package com.restapp.Restaurant.model;

import javax.persistence.*;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pizzaId;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "good")
    private Good good;
    private Boolean isVegetarian;
    private Boolean isSpicy;
    private String pizzaImg;

    public Pizza(Good good, Boolean isVegetarian, Boolean isSpicy, String pizzaImg) {
        this.good = good;
        this.isVegetarian = isVegetarian;
        this.isSpicy = isSpicy;
        this.pizzaImg = pizzaImg;
    }

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

    public String getPizzaImg() {
        return pizzaImg;
    }

    public void setPizzaImg(String pizzaImg) {
        this.pizzaImg = pizzaImg;
    }
}
