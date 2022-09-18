package com.restapp.Restaurant.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer drinkId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "good")
    private Good good;
    private Boolean isAlcohol;
    private Boolean isWarm;
    private Boolean isGazed;
    private Boolean hasCaffeine;
    private String drinkImg;
}
