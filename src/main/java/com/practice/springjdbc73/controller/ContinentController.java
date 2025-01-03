package com.practice.springjdbc73.controller;

import com.practice.springjdbc73.dao.ContinentDao;
import com.practice.springjdbc73.model.Continent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/continents")
public class ContinentController {
    private final ContinentDao continentDao;

    @GetMapping
    public List<Continent> findAll() {
        return continentDao.findAll();
    }

    @GetMapping("/{id}")
    public Continent findById(@PathVariable int id) {
        return continentDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Continent create(@RequestBody Continent continent) {
        return continentDao.create(continent);
    }

    @PutMapping
    public Continent update(@RequestBody Continent continent) {
        return continentDao.update(continent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        continentDao.deleteById(id);
    }
}
