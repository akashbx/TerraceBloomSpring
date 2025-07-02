package com.example.terracebloom.Dto;

import com.example.terracebloom.Entity.Product;
import com.example.terracebloom.Entity.Gardener;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private Integer id;
    private String type;
    private String name;
    private String image;
    private Double price;

    public static CartItemDto fromProduct(Product product, Integer id) {
        return CartItemDto.builder()
                .id(id)
                .type("PRODUCT")
                .name(product.getName())
                .image(product.getImage())
                .price(Double.valueOf(product.getPrice()))
                .build();
    }

    public static CartItemDto fromGardener(Gardener gardener, Integer id) {
        return CartItemDto.builder()
                .id(id)
                .type("GARDENER")
                .name(gardener.getName())
                .image(gardener.getImage())
                .price(Double.valueOf(gardener.getPrice()))
                .build();
    }
}

