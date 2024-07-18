package com.example.smartBar.smartBar.services.impl;

import com.example.smartBar.smartBar.domain.OrderEntity;
import com.example.smartBar.smartBar.dto.OrderCreationDto;
import com.example.smartBar.smartBar.dto.OrderDto;
import com.example.smartBar.smartBar.repository.OrderRepository;
import com.example.smartBar.smartBar.services.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;
    private  ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger( OrderServiceImpl.class);


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }




    @Override
    public String makeOrder(OrderDto orderDto) {

        // create order
        String reference = "090900991";
        OrderCreationDto order = new OrderCreationDto(
                (Long) null,
                15L,
                reference,
                orderDto.getTotalPrice(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        OrderEntity newOrder = modelMapper.map(order, OrderEntity.class);
        orderRepository.save(newOrder);
        // create menuItems
        return "Order created";
    }
}
