package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.CartDto;
import com.example.terracebloom.Dto.CartItemDto;
import com.example.terracebloom.Entity.*;
import com.example.terracebloom.Repository.*;
import com.example.terracebloom.Request.AddToCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private GardenerRepository gardenerRepository;
    @Autowired private CartItemRepository cartItemRepository;

    public Cart addToCart(AddToCartRequest request) {
        User user = userRepository.findById(Math.toIntExact(request.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(null, user, new ArrayList<>())));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setQuantity(request.getQuantity());

        if (request.getProductId() != null) {
            Product product = productRepository.findById(Math.toIntExact(request.getProductId()))
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            item.setProduct(product);
        }

        if (request.getGardenerServiceId() != null) {
            Gardener service = gardenerRepository.findById(request.getGardenerServiceId().intValue())
                    .orElseThrow(() -> new RuntimeException("Service not found"));
            item.setGardener(service);
        }

        cart.getItems().add(item);
        cartRepository.save(cart);
        return cart;
    }

    public CartDto viewCart(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItemDto> itemDtos = cart.getItems().stream()
                .map(item -> {
                    if (item.getProduct() != null) {
                        return CartItemDto.fromProduct(item.getProduct(), item.getId().intValue());
                    } else if (item.getGardener() != null) {
                        return CartItemDto.fromGardener(item.getGardener(), item.getId().intValue());
                    } else {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());

        double total = itemDtos.stream().mapToDouble(CartItemDto::getPrice).sum();

        return CartDto.builder()
                .userId(userId)
                .items(itemDtos)
                .totalPrice(total)
                .build();
    }

    public void removeItemFromCart(Integer userId, Integer cartItemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(cartItemId.longValue())
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cart.getItems().contains(item)) {
            throw new RuntimeException("Item does not belong to user's cart");
        }

        cart.getItems().remove(item);
        cartItemRepository.delete(item);
        cartRepository.save(cart);
    }
}
