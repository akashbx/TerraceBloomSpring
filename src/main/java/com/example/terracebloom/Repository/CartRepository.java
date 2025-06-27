package com.example.terracebloom.Repository;

import com.example.terracebloom.Entity.Cart;
import com.example.terracebloom.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
