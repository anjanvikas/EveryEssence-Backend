package com.everyessence.everyessence_backend.repository;

import com.everyessence.everyessence_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}