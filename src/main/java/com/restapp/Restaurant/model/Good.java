package com.restapp.Restaurant.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodId;
    private String goodName;
    private Double goodPrice;
    private String goodDescription;
    private String goodImage;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category goodCategory;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "goods_ingredients",
               joinColumns = @JoinColumn(name = "goods"),
               inverseJoinColumns = @JoinColumn(name = "ingredients"))
    private Set<Ingredient> goodIngredients;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "goods_properties",
               joinColumns = @JoinColumn(name = "goods"),
               inverseJoinColumns = @JoinColumn(name = "properties"))
    private Set<Property> goodProperties;
}
