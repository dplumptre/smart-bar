package com.example.smartBar.smartBar.services.impl;

import com.example.smartBar.smartBar.controller.OrderController;
import com.example.smartBar.smartBar.domain.OrderEntity;
import com.example.smartBar.smartBar.domain.OrderItemEntity;
import com.example.smartBar.smartBar.domain.PaymentMethodEntity;
import com.example.smartBar.smartBar.domain.UserEntity;
import com.example.smartBar.smartBar.dto.*;
import com.example.smartBar.smartBar.exception.ResourceNotFoundException;
import com.example.smartBar.smartBar.repository.OrderItemRepository;
import com.example.smartBar.smartBar.repository.OrderRepository;
import com.example.smartBar.smartBar.repository.PaymentMethodRepository;
import com.example.smartBar.smartBar.repository.UserRepository;
import com.example.smartBar.smartBar.services.OrderService;
import com.example.smartBar.smartBar.util.Helper;
import com.example.smartBar.smartBar.util.SecurityUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private UserRepository userRepository;

    private PaymentMethodRepository paymentMethodRepository;
    private final ModelMapper modelMapper;

    @Value("${app.prefix}")
    private String prefix;

    Logger logger = LoggerFactory.getLogger( OrderServiceImpl.class);


    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, PaymentMethodRepository paymentMethodRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }




    @Override
    public String makeOrder(OrderDto orderDto) {


        String reference = prefix + Helper.generateRandomString(10);
        UserEntity userEntity = userRepository.findByPhoneNumber(SecurityUtil.getCurrentUsername())
                .orElseThrow( ()-> new ResourceNotFoundException("user not found!"));
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        PaymentMethodEntity paymentMethod = paymentMethodRepository.findById(orderDto.getPaymentMethodId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found!"));
        PaymentMethodDto paymentMethodDto = modelMapper.map(paymentMethod,PaymentMethodDto.class);


        // create order
        OrderCreationDto order = new OrderCreationDto(
                (Long) null,
                userDto,
                reference,
                paymentMethodDto,
                orderDto.getTotalPrice(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        OrderEntity newOrder = modelMapper.map(order, OrderEntity.class);
        newOrder.setPaymentMethod(paymentMethod);
        orderRepository.save(newOrder);

        //  creating the orderItems
        for (SelectedMenuDto menu : orderDto.getMenuItemEntities()) {
            OrderItemDto orderItem =   new OrderItemDto();
            orderItem.setPrice(menu.getPrice());
            orderItem.setQuantity(menu.getQuantity());
            orderItem.setOrderId(newOrder.getId());
            orderItem.setMenuItemId(menu.getId());
            OrderItemEntity orderItemEntity = modelMapper.map(orderItem,OrderItemEntity.class);
            orderItemRepository.save(orderItemEntity);
        }

        return "Order created";



    }

    @Override
    public List<OrderCreationDto> allOrders() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    OrderCreationDto orderDto = modelMapper.map(order, OrderCreationDto.class);
                    UserDto userDto = modelMapper.map(order.getUser(), UserDto.class);
                    PaymentMethodDto paymentMethodDto = modelMapper.map(order.getPaymentMethod(), PaymentMethodDto.class);
                    orderDto.setUserId(userDto);
                    orderDto.setPaymentMethodId(paymentMethodDto);
                    return orderDto;
                })
                .collect(Collectors.toList());
    }


}
