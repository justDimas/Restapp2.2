package com.restapp.Restaurant.model;

import javax.persistence.*;

@Entity
public class Salad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saladId;
    @OneToOne
    @JoinColumn(name = "good")
    private Good good;
    private Boolean isVegetarian;
    private Boolean isCold;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "character varying default 'noimg.jpg'")
    private String saladImg;
    @Transient
    private String name;
    @Transient
    private Double price;

    public Salad() {
    }

    public Integer getSaladId() {
        return saladId;
    }

    public void setSaladId(Integer saladId) {
        this.saladId = saladId;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Boolean getVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Boolean getCold() {
        return isCold;
    }

    public void setCold(Boolean cold) {
        isCold = cold;
    }

    public String getSaladImg() {
        return saladImg;
    }

    public void setSaladImg(String saladImg) {
        this.saladImg = saladImg;
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
