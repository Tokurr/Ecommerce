package com.app.ecom.controller;

import com.app.ecom.dto.OrderDto;
import com.app.ecom.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestHeader("X-User-ID") String userId)
    {
        OrderDto orderDto = orderService.createOrder(userId);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);

    }

}
