package com.restapp.Restaurant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PizzaSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pizzaSizeId;
    private String pizzaSizeName;

    public PizzaSize() {
    }

    public Integer getPizzaSizeId() {
        return pizzaSizeId;
    }

    public void setPizzaSizeId(Integer pizzaSizeId) {
        this.pizzaSizeId = pizzaSizeId;
    }

    public String getPizzaSizeName() {
        return pizzaSizeName;
    }

    public void setPizzaSizeName(String pizzaSizeName) {
        this.pizzaSizeName = pizzaSizeName;
    }
}
