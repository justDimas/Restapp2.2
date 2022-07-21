package com.restapp.Restaurant.model;

import javax.persistence.*;

@Entity
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer drinkId;
    @OneToOne
    @JoinColumn(name = "good")
    private Good good;
    private Boolean isAlcohol;
    private Boolean isCold;
    private Boolean isGazed;
    private Boolean hasCaffeine;

    public Drink() {
    }

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

    public Boolean getCold() {
        return isCold;
    }

    public void setCold(Boolean cold) {
        isCold = cold;
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
}
