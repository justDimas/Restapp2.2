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
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    @OneToMany(mappedBy = "goodCategory", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private Set<Good> categoryGood;
    @Transient
    @JsonIgnore
    private static final String regexCheckName = "^[А-Яа-я0-9]{2,32}$";

    public boolean isValidName(){
        return categoryName.matches(regexCheckName);
    }
}