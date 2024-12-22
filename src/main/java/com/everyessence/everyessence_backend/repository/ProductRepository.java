package com.everyessence.everyessence_backend.repository;

import com.everyessence.everyessence_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}