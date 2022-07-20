package com.restapp.Restaurant.model.goodsModel;

import javax.persistence.*;

@Entity
public class Pizza {
    @Id
    private Integer pizzaId;
    private Boolean isVegetarian;
    private Boolean isSpicy;
    @OneToOne
    private Good good;
    @ManyToOne
    private PizzaSize pizzaSize;

    public Pizza() {
    }

    public Integer getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Integer pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Boolean getVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Boolean getSpicy() {
        return isSpicy;
    }

    public void setSpicy(Boolean spicy) {
        isSpicy = spicy;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public PizzaSize getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(PizzaSize pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

}
