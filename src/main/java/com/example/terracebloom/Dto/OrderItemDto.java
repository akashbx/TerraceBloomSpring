package com.example.terracebloom.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private String type; // "PRODUCT" or "GARDENER"
    private String name;
    private int quantity;
    private double price;
}

