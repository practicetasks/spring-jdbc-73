package com.practice.springjdbc73.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// У вас должен быть класс Category
public class Continent {
    private int id;
    private String name;
}
