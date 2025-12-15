package com.app.ecom.dto.convertor;

import com.app.ecom.dto.OrderDto;
import com.app.ecom.dto.OrderItemDto;
import com.app.ecom.model.Order;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class OrderDtoConvertor {

    public OrderDto convert(Order order)
    {


        OrderDto orderDto = new OrderDto(order.getId(),
                order.getTotalAmount(),
                order.getStatus(),order.getItemList().stream()
                .map(orderItem -> new OrderItemDto(orderItem.getId(),
                        orderItem.getProduct().getId(),orderItem.getQuantity(),
                        orderItem.getPrice(), orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        )).collect(Collectors.toList()),order.getCreatedAt());

        return orderDto;
    }



}
