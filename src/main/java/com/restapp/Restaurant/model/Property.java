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
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;
    private String propertyName;
    private String propertyImage;
    @ManyToMany(mappedBy = "goodProperties", fetch = FetchType.EAGER)
    private Set<Good> propertyGoods;
}
