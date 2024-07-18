package com.example.smartBar.smartBar.services;

import com.example.smartBar.smartBar.dto.OrderDto;
import com.example.smartBar.smartBar.dto.OrderItemDto;
import org.hibernate.query.Order;

import java.util.List;

public interface OrderService {

    String makeOrder(OrderDto orderDto);
}
