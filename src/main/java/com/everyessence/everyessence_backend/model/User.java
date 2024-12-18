package com.everyessence.everyessence_backend.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    private String googleId;  // For storing Google OAuth ID
    private String profilePicture; // Link to Google profile picture

    @OneToMany(mappedBy = "user")  
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<WishList> wishlists;

    @OneToMany(mappedBy = "user")
    private List<ShippingAddress> shippingAddresses;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    // Other user-related fields can be added here, such as roles
}
