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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    @ManyToOne
    @JoinColumn(name = "unit")
    private Unit unit;
    private Double productCount;
    private Double productPrice;
}
