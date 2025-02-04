package com.example.smartBar.smartBar.services;

import com.example.smartBar.smartBar.domain.OrderItemEntity;
import com.example.smartBar.smartBar.dto.OrderCreationDto;
import com.example.smartBar.smartBar.dto.OrderDto;
import com.example.smartBar.smartBar.dto.OrderItemDto;
import com.example.smartBar.smartBar.dto.OrderNumberDto;
import org.hibernate.query.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    OrderCreationDto makeOrder(OrderDto orderDto, Long paymentMethodId);

    List<OrderDto> allOrders();

//    List<OrderDto> getOrdersByUserId(Long id);

    List<String>allOrderNumbers();

    OrderDto getOrder(Long id);


    List<OrderCreationDto> getOrdersByUserAndStatus(Long userId);

    OrderCreationDto updateOrderStatus(Long orderId, OrderCreationDto status);
}
