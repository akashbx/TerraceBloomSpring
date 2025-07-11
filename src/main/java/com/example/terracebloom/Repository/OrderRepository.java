package com.example.terracebloom.Repository;

import com.example.terracebloom.Entity.Order;
import com.example.terracebloom.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByUser(User user);

    List<Order> findOrderByUserId(Integer userId);
}
