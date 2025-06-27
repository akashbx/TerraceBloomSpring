package com.example.terracebloom.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {
    private Long userId;
    private Long productId; // Optional
    private Long gardenerServiceId; // Optional
    private int quantity;
}

