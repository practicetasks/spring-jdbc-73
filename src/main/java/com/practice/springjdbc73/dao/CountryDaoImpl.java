package com.practice.springjdbc73.dao;

import com.practice.springjdbc73.model.Continent;
import com.practice.springjdbc73.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CountryDaoImpl implements CountryDao {
    private final JdbcTemplate jdbcTemplate;
    private static final String SELECT = """
            select countries.id    as country_id,
                   countries.name  as country_name,
                   continents.id   as continent_id,
                   continents.name as continent_name
            from countries
            join continents on countries.continent_id = continents.id
            """;

    @Override
    public Country findById(int id) {
        String sql = SELECT + "where countries.id = ?";
        return jdbcTemplate.query(sql, this::mapRow, id)
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(SELECT, this::mapRow);
    }

    @Transactional
    @Override
    public Country create(Country country) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("countries")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> map = Map.of(
                "name", country.getName(),
                "continent_id", country.getContinent().getId()
        );

        int id = insert.executeAndReturnKey(map).intValue();
        country.setId(id);

        for (Integer languageId : country.getLanguages()) {
            String sql = "insert into countries_languages (country_id, language_id) values (?, ?)";
            jdbcTemplate.update(sql, country.getId(), languageId);
        }

        return country;
    }

    @Override
    public Country update(Country country) {
        // findById(country.getId());

        String sql = "update countries set name = ? where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, country.getName(), country.getId());
        if (rowsAffected == 0) {
            throw new RuntimeException();
        }

        return country;
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from countries where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException();
        }
    }

    private Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        int countryId = rs.getInt("country_id");
        String countryName = rs.getString("country_name");

        int continentId = rs.getInt("continent_id");
        String continentName = rs.getString("continent_name");
        Continent continent = new Continent(continentId, continentName);

        return new Country(countryId, countryName, continent);
    }
}
