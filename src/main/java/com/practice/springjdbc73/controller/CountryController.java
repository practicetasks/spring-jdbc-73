package com.practice.springjdbc73.controller;

import com.practice.springjdbc73.dao.CountryDao;
import com.practice.springjdbc73.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {
    private final CountryDao countryDao;

    @GetMapping("/{id}")
    public Country findById(@PathVariable int id) {
        return countryDao.findById(id);
    }

    @GetMapping
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryDao.create(country);
    }

    @PutMapping
    public Country update(@RequestBody Country country) {
        return countryDao.update(country);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        countryDao.deleteById(id);
    }
}
