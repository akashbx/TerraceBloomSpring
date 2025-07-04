package com.example.terracebloom.Dto;

import com.example.terracebloom.Entity.Gardener;
import com.example.terracebloom.Entity.Order;
import com.example.terracebloom.Entity.Product;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Integer id;
    private Integer userId;
    private String status;
    private Double totalPrice;
    private List<OrderItemDto> items;
    private LocalDate placedDate;

    public static OrderDto from(Order order) {
        List<OrderItemDto> itemDtos = order.getItems().stream().map(item -> {
            if (item.getProduct() != null) {
                return OrderItemDto.builder()
                        .type("PRODUCT")
                        .name(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .price(item.getProduct().getPrice())
                        .build();
            } else {
                return OrderItemDto.builder()
                        .type("GARDENER")
                        .name(item.getGardener().getName())
                        .quantity(item.getQuantity())
                        .price(item.getGardener().getPrice())
                        .build();
            }
        }).toList();

        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .status(order.getStatus())
                .placedDate(order.getPlacedDate())
                .totalPrice(order.getTotalPrice())
                .items(itemDtos)
                .build();
    }
}

