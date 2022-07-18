package com.restapp.Restaurant.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private List<Ingredient> ingredients;

}
