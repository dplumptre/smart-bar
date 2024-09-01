package com.example.smartBar.smartBar.services;

import com.example.smartBar.smartBar.dto.OrderCreationDto;
import com.example.smartBar.smartBar.dto.OrderDto;
import com.example.smartBar.smartBar.dto.OrderItemDto;
import org.hibernate.query.Order;

import java.util.List;

public interface OrderService {

    OrderCreationDto makeOrder(OrderDto orderDto);

    List<OrderDto> allOrders();

    OrderDto getOrder(Long id);
}
