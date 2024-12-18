package com.everyessence.everyessence_backend.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
import java.util.Locale.Category;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imageUrl;

    @ManyToOne
    private Category category;  // For sorting products by category

    @ManyToMany(mappedBy = "products")
    private List<WishList> wishlists;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews; // User reviews for products
}
