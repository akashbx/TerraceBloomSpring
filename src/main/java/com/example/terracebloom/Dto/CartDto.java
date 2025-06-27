package com.example.terracebloom.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private Integer userId;
    private List<CartItemDto> items;
    private Double totalPrice;
}

