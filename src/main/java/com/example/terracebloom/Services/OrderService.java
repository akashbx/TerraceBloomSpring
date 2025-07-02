package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.OrderDto;
import com.example.terracebloom.Entity.*;
import com.example.terracebloom.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GardenerRepository gardenerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public OrderDto placeOrderFromCart(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<Product> products = cart.getItems().stream()
                .filter(item -> item.getProduct() != null)
                .map(CartItem::getProduct)
                .toList();

        List<Gardener> gardeners = cart.getItems().stream()
                .filter(item -> item.getGardener() != null)
                .map(CartItem::getGardener)
                .toList();

        double totalPrice = products.stream().mapToDouble(Product::getPrice).sum() +
                gardeners.stream().mapToDouble(Gardener::getPrice).sum();

        Order order = Order.builder()
                .user(user)
                .products(products)
                .gardeners(gardeners)
                .status("PLACED")
                .totalPrice(totalPrice)
                .build();

        Order saved = orderRepository.save(order);

        // 🧹 Clear cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return OrderDto.from(saved);
    }

    public OrderDto cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        if (order.getStatus().equalsIgnoreCase("CANCELLED")) {
            throw new RuntimeException("Order is already cancelled.");
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);

        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .productIds(order.getProducts().stream().map(Product::getId).toList())
                .gardenerIds(order.getGardeners().stream().map(Gardener::getId).toList())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }
    public OrderDto getOrderHistory(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order = orderRepository.findByUser(user);
        if (order == null) {
            throw new RuntimeException("Order not found for user with id: " + userId);
        }
        return OrderDto.from(order);
    }
}
