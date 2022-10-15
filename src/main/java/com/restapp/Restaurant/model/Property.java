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
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;
    private String propertyName;
    private String propertyImage;
    @ManyToMany(mappedBy = "goodProperties")
    @ToString.Exclude
    @JsonIgnore
    private Set<Good> propertyGoods;
    @Transient
    @JsonIgnore
    private static final String regexCheckName = "^[А-Яа-я0-9]{2,32}$";
    @Transient
    @JsonIgnore
    private static final String regexCheckImage = "^[А-Яа-яA-Za-z0-9\\s\\_\\-\\,\\.]{1,32}\\.[A-Za-z0-9]{1,8}$";

    public boolean isValidName(){
        return propertyName.matches(regexCheckName);
    }
    public boolean isValidImage(){
        return propertyImage.matches(regexCheckImage);
    }
}
