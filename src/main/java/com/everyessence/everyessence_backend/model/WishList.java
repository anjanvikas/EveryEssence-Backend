package com.everyessence.everyessence_backend.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
      name = "wishlist_products", 
      joinColumns = @JoinColumn(name = "wishlist_id"), 
      inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products; 
}
