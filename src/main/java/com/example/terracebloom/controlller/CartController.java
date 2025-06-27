package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.CartDto;
import com.example.terracebloom.Entity.Cart;
import com.example.terracebloom.Request.AddToCartRequest;
import com.example.terracebloom.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(request));
    }


    @GetMapping("/view/{userId}")
    public ResponseEntity<CartDto> viewCart(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartService.viewCart(userId));
    }


    @DeleteMapping("/remove/{userId}/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable Integer userId,
                                             @PathVariable Integer itemId) {
        cartService.removeItemFromCart(userId, itemId);
        return ResponseEntity.ok("Item removed from cart successfully.");
    }
}

