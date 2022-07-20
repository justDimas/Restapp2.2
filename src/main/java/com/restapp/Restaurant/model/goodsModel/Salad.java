package com.restapp.Restaurant.model.goodsModel;

import javax.persistence.*;

@Entity
public class Salad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saladId;
    private Boolean isVegetarian;
    private Boolean isCold;
    @OneToOne
    private Good good;
}
