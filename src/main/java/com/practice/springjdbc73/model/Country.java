package com.practice.springjdbc73.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data // @Getter, @Setter, @EqualsAndHashCode, @ToString, @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private int id;
    private String name;
    private Continent continent;
    private final List<Integer> languages = new ArrayList<>();
}
