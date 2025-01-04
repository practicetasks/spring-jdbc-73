package com.practice.springjdbc73.dao;

import com.practice.springjdbc73.model.Country;

import java.util.List;

public interface CountryDao {
    Country findById(int id);

    List<Country> findAll();

    Country create(Country country);

    Country update(Country country);

    void deleteById(int id);
}
