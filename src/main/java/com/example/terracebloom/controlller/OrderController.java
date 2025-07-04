package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.OrderDto;
import com.example.terracebloom.Dto.ResponseMessage;
import com.example.terracebloom.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/from-cart/{userId}")
    public ResponseEntity<ResponseMessage> placeOrderFromCart(@PathVariable Integer userId) {
        orderService.placeOrderFromCart(userId);
        return ResponseEntity.ok(new ResponseMessage("success","Order placed successfully"));
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @GetMapping("History/{userId}")
    public ResponseEntity<List<OrderDto>> getOrderHistory(@PathVariable Integer userId) {
        return ResponseEntity.ok(orderService.getOrderHistory(userId));
    }

}

