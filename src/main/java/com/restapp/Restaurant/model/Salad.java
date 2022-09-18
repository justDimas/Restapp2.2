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
public class Salad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saladId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "good")
    private Good good;
    private Boolean isVegetarian;
    private Boolean isWarm;
    private String saladImg;
}
