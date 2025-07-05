package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.OrderDto;
import com.example.terracebloom.Entity.*;
import com.example.terracebloom.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<CartItem> cartItems = new ArrayList<>(cart.getItems()); // snapshot of current items

        double totalPrice = cartItems.stream()
                .mapToDouble(item -> {
                    if (item.getProduct() != null) {
                        return item.getProduct().getPrice();
                    } else if (item.getGardener() != null) {
                        return item.getGardener().getPrice() * item.getQuantity();
                    }
                    return 0.0;
                })
                .sum();

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PLACED");
        order.setTotalPrice(totalPrice);
        order.setPlacedDate(LocalDate.now());
        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setQuantity(cartItem.getQuantity());

            if (cartItem.getProduct() != null) {
                orderItem.setProduct(cartItem.getProduct());
            } else if (cartItem.getGardener() != null) {
                orderItem.setGardener(cartItem.getGardener());
            }

            return orderItem;
        }).collect(Collectors.toList());

        savedOrder.setItems(orderItems);
        orderRepository.save(savedOrder);

        cart.getItems().clear();
        cartRepository.save(cart);

        return OrderDto.from(savedOrder);
    }



    public OrderDto cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        if (order.getStatus().equalsIgnoreCase("CANCELLED")) {
            throw new RuntimeException("Order is already cancelled.");
        }
        order.setPlacedDate(LocalDate.now());
        order.setStatus("CANCELLED");
        orderRepository.save(order);

        return OrderDto.from(order);
    }
    public List<OrderDto> getOrderHistory(Integer userId) {
        List<Order> orders = orderRepository.findOrderByUserId(userId);
        return orders.stream().map(OrderDto::from).collect(Collectors.toList());
    }

}
