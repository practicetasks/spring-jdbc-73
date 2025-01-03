package com.practice.springjdbc73.dao;

import com.practice.springjdbc73.model.Continent;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ContinentDaoImpl implements ContinentDao {
    private final JdbcTemplate jdbcTemplate;

    // .queryForRowSet - для отправки select запроса, возвращает тип SqlRowSet
    // .update - для отправки insert/update/delete запросов
    // .query - для отправки select запросов, возвращает список объектов

    // ORM (Object Relational Mapping)

    @Override
    public List<Continent> findAll() {
//        String sql = "select * from continents";
//        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
//
//        List<Continent> result = new ArrayList<>();
//        while (sqlRowSet.next()) {
//            int id = sqlRowSet.getInt("id");
//            String name = sqlRowSet.getString("name");
//            Continent continent = new Continent(id, name);
//            result.add(continent);
//        }
//        return result;

        // RowMapper - функциональный интерфейс, в котором необходимо описать как преобразовать результат в объект

//        RowMapper<Continent> rowMapper = new RowMapper<>() {
//            @Override
//            public Continent mapRow(ResultSet rs, int rowNum) throws SQLException {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                return new Continent(id, name);
//            }
//        };

//        RowMapper<Continent> rowMapper = (ResultSet rs, int rowNum) -> {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            return new Continent(id, name);
//        };

        // BeanPropertyRowMapper

        String sql = "select * from continents";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    @Override
    public Continent findById(int id) {
        String sql = "select * from continents where id = ?";
        return jdbcTemplate.query(sql, this::mapRow, id)
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Continent create(Continent continent) {
        // insert into continents (name) values (?)
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("continents")  // categories
                .usingGeneratedKeyColumns("id");

        Map<String, Object> map = Map.of(
                "name", continent.getName()
        );

        int id = simpleJdbcInsert.executeAndReturnKey(map).intValue();
        continent.setId(id);
        return continent;
    }

    @Override
    public Continent update(Continent continent) {
        String sql = "update continents set name = ? where id = ?";
        jdbcTemplate.update(sql, continent.getName(), continent.getId());
        return continent;
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from continents where id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Continent mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        return new Continent(id, name);
    }
}
