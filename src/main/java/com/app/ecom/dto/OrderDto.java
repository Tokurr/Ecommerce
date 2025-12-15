package com.app.ecom.dto;

import com.app.ecom.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private String id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> orderItemList;
    private LocalDateTime createdTime;

}
