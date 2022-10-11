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

/*TODO
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseId;
    private LocalDateTime purchaseTime;
    private String purchaseCommentary;
    @ManyToMany
    @JoinTable(name="purchases-goods",
               joinColumns = @JoinColumn(name = "purchases"),
               inverseJoinColumns = @JoinColumn(name="goods"))
    private Set<Good> purchaseGoods;
    @ManyToOne
    @JoinColumn(name = "c_user")
    private CustomUser purchaseUser;
}
*/