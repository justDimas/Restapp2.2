package com.restapp.Restaurant.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Salad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private List<Ingredient> ingredients;
    private Double price;
}
