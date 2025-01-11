package com.practice.springjdbc73.dao;

import com.practice.springjdbc73.model.Continent;

import java.util.List;

// У вас должен быть интерфейс CategoryDao
public interface ContinentDao {
    List<Continent> findAll();

    Continent findById(int id);

    Continent create(Continent continent);

    Continent update(Continent continent);

    void deleteById(int id);

    void create100_000();

    void create100_000Batch();
}
