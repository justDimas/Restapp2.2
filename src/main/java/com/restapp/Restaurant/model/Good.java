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
    @ManyToMany
    @JoinTable(name = "goods_ingredients",
               joinColumns = @JoinColumn(name = "goods"),
               inverseJoinColumns = @JoinColumn(name = "ingredients"))
    @ToString.Exclude
    private Set<Ingredient> goodIngredients;
    @ManyToMany
    @JoinTable(name = "goods_properties",
               joinColumns = @JoinColumn(name = "goods"),
               inverseJoinColumns = @JoinColumn(name = "properties"))
    @ToString.Exclude
    private Set<Property> goodProperties;
    @Transient
    @JsonIgnore
    private static final String regexCheckName = "^[A-Za-zА-Яа-я0-9\\s\\,\\.\\-\\_\\\"]{2,32}$";
    @Transient
    @JsonIgnore
    private static final String regexCheckDescription = "^[A-Za-zА-Яа-я0-9\\s\\,\\.\\-\\_\\\"]{2,256}$";
    @Transient
    @JsonIgnore
    private static final String regexCheckImage = "^[А-Яа-яA-Za-z0-9\\s\\_\\-\\,\\.]{1,32}\\.[A-Za-z0-9]{1,8}$";
    @Transient
    @JsonIgnore
    private static final Double minPrice = 0d;
    @Transient
    @JsonIgnore
    private static final Double maxPrice = 1000d;

    public boolean isValidName(){
        return goodName.matches(regexCheckName);
    }
    public boolean isValidDescription(){
        return goodDescription.matches(regexCheckDescription);
    }
    public boolean isValidImage(){
        return goodImage.matches(regexCheckImage);
    }
    public boolean isValidPrice(){
        return (goodPrice>minPrice && goodPrice<maxPrice);
    }
}
