package com.myproject.resource_server.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cart extends BaseModel {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CartItem> cartItemList;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Column(name = "total_cart_price")
    private Float totalCartPrice;

    @Column(name = "total_cargo_price")
    private Float totalCargoPrice;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "date_created", insertable = false)
    private Date dateCreated;

}

