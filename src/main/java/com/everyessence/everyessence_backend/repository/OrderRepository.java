package com.everyessence.everyessence_backend.repository;

import com.everyessence.everyessence_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}