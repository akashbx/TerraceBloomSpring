package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.OrderDto;
import com.example.terracebloom.Request.OrderRequest;
import com.example.terracebloom.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/from-cart/{userId}")
    public ResponseEntity<OrderDto> placeOrderFromCart(@PathVariable Integer userId) {
        return ResponseEntity.ok(orderService.placeOrderFromCart(userId));
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }
    @GetMapping("History/{userId}")
    public ResponseEntity<OrderDto> getOrderHistory(@PathVariable Integer userId) {
        return ResponseEntity.ok(orderService.getOrderHistory(userId));
    }
}

