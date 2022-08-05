package com.restapp.Restaurant.model;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer drinkId;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "good")
    private Good good;
    private Boolean isAlcohol;
    private Boolean isWarm;
    private Boolean isGazed;
    private Boolean hasCaffeine;
    private String drinkImg;
    @Transient
    private String name;
    @Transient
    private Double price;

    public Drink(Good good, Boolean hasCaffeine,
                 Boolean isAlcohol, Boolean isWarm,
                 Boolean isGazed, String drinkImg) {
        this.good = good;
        this.hasCaffeine = hasCaffeine;
        this.isAlcohol = isAlcohol;
        this.isWarm = isWarm;
        this.isGazed = isGazed;
        this.drinkImg = drinkImg;
    }

    public Drink() { }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Boolean getAlcohol() {
        return isAlcohol;
    }

    public void setAlcohol(Boolean alcohol) {
        isAlcohol = alcohol;
    }

    public Boolean getWarm() {
        return isWarm;
    }

    public void setWarm(Boolean warm) {
        isWarm = warm;
    }

    public Boolean getGazed() {
        return isGazed;
    }

    public void setGazed(Boolean gazed) {
        isGazed = gazed;
    }

    public Boolean getHasCaffeine() {
        return hasCaffeine;
    }

    public void setHasCaffeine(Boolean hasCaffeine) {
        this.hasCaffeine = hasCaffeine;
    }

    public String getDrinkImg() {
        return drinkImg;
    }

    public void setDrinkImg(String drinkImg) {
        this.drinkImg = drinkImg;
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
}
