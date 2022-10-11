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
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;
    private String ingredientName;
    private Double ingredientWeight;
    @ManyToOne
    @JoinColumn(name = "unit")
    private Unit ingredientUnit;
    @ManyToMany(mappedBy = "goodIngredients", fetch = FetchType.EAGER)
    private Set<Good> ingredientGoods;
}
