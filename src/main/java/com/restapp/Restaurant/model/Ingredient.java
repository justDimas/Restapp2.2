package com.restapp.Restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToMany(mappedBy = "goodIngredients")
    @JsonIgnore
    @ToString.Exclude
    private Set<Good> ingredientGoods;
    @Transient
    @JsonIgnore
    private static final String regexCheckName = "^[А-Яа-я0-9\\s]{2,32}$";
    @Transient
    @JsonIgnore
    private static final Double minWeight = 0d;
    @Transient
    @JsonIgnore
    private static final Double maxWeight = 1000d;

    public boolean isValidName(){
        return ingredientName.matches(regexCheckName);
    }
    public boolean isValidPrice(){
        return (ingredientWeight>minWeight&&ingredientWeight<maxWeight);
    }
}
