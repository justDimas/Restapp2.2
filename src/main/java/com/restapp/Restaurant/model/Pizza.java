package com.restapp.Restaurant.model;

import javax.persistence.*;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pizzaId;
    @OneToOne
    @JoinColumn(name = "good")
    private Good good;
    private Boolean isVegetarian;
    private Boolean isSpicy;
    @Column(columnDefinition = "character varying(255) DEFAULT 'noimg.jpg'")
    private String pizzaImg;
    @Transient
    private String name;
    @Transient
    private Double price;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPizzaImg() {
        return pizzaImg;
    }

    public void setPizzaImg(String pizzaImg) {
        this.pizzaImg = pizzaImg;
    }
}
