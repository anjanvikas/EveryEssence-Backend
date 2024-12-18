package com.everyessence.everyessence_backend.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;  // Items in the user's cart
}