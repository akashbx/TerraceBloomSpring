package com.example.terracebloom.Dto;

import com.example.terracebloom.Entity.Gardener;
import com.example.terracebloom.Entity.Order;
import com.example.terracebloom.Entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Integer id;
    private Integer userId;
    private List<Integer> productIds;
    private List<Integer> gardenerIds;
    private String status;
    private Double totalPrice;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .productIds(order.getProducts().stream().map(Product::getId).toList())
                .gardenerIds(order.getGardeners().stream().map(Gardener::getId).toList())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}

