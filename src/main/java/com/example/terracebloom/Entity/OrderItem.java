package com.example.terracebloom.Entity;

import com.example.terracebloom.Entity.Gardener;
import com.example.terracebloom.Entity.Order;
import com.example.terracebloom.Entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Gardener gardener;

    private int quantity;
}
