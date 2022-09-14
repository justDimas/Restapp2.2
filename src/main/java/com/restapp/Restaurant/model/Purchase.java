package com.restapp.Restaurant.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleId;
    private LocalDateTime saleTime;
    private String commentary;
    @ManyToOne
    @JoinColumn(name = "good")
    private Good good;
    @ManyToOne
    @JoinColumn(name = "t_user")
    private CustomUser user;
}
