package com.restapp.Restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unitId;
    private String unitName;
    @Transient
    @JsonIgnore
    private static final String regexCheckName = "^[А-Яа-я0-9]{2,32}$";

    public boolean isValidName(){
        return unitName.matches(regexCheckName);
    }
}
