package com.app.ecom.service;

import com.app.ecom.dto.OrderDto;
import com.app.ecom.dto.convertor.OrderDtoConvertor;
import com.app.ecom.model.*;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    private final OrderDtoConvertor orderDtoConvertor;

    public OrderService(CartService cartService, UserRepository userRepository, OrderRepository orderRepository, OrderDtoConvertor orderDtoConvertor) {
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderDtoConvertor = orderDtoConvertor;
    }

    @Transactional
    public OrderDto createOrder(String userId) {

        List<CartItem> cartItemList = cartService.getCardByUserId(userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found by id: " + userId));
        if(cartItemList.isEmpty()){

            throw new RuntimeException("Cart item not found");
        }



        BigDecimal totalPrice = cartItemList.stream().map(cartItem -> cartItem.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItemList.stream().map(item -> new OrderItem(null,item.getProduct(),
                item.getQuantity(),item.getPrice(),order))
                .collect(Collectors.toList());

        order.setItemList(orderItems);

        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(userId);

        return orderDtoConvertor.convert(savedOrder);

    }
}
