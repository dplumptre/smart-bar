package com.example.smartBar.smartBar.services.impl;

import com.example.smartBar.smartBar.controller.OrderController;
import com.example.smartBar.smartBar.domain.*;
import com.example.smartBar.smartBar.dto.*;
import com.example.smartBar.smartBar.enums.StatusType;
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
    public OrderCreationDto makeOrder(OrderDto orderDto, Long paymentMethodId) {


        if(orderDto.getMenuItemEntities().isEmpty()){
            throw new ResourceNotFoundException("There is no item on this order!");
        }

        logger.info(String.valueOf(orderDto));
        String reference = prefix + Helper.generateRandomString(10);
        UserEntity userEntity = userRepository.findByPhoneNumber(SecurityUtil.getCurrentUsername())
                .orElseThrow( ()-> new ResourceNotFoundException("user not found!"));

        PaymentMethodEntity paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found!"));


        OrderEntity newOrder = modelMapper.map(orderDto, OrderEntity.class);
        newOrder.setUser(userEntity);
        newOrder.setOrderReference(reference);
        newOrder.setStatus(StatusType.PENDING.name());
        newOrder.setPaymentMethod(paymentMethod);
        OrderEntity savedNewOrder = orderRepository.save(newOrder);


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

        return modelMapper.map(savedNewOrder,OrderCreationDto.class);



    }

    @Override
    public List<OrderDto> allOrders() {

        return orderRepository.findAllByOrderByIdDesc().stream()
                .map(order -> {
                    OrderDto orderDto = modelMapper.map(order, OrderDto.class);
                    UserDto userDto = modelMapper.map(order.getUser(), UserDto.class);
                    PaymentMethodDto paymentMethodDto = modelMapper.map(order.getPaymentMethod(), PaymentMethodDto.class);
                    orderDto.setPaymentMethod(paymentMethodDto);
                    List<SelectedMenuDto> selectedMenuDtos = order.getOrderItems().stream()
                            .map(orderItem -> {
                                MenuItemEntity menuItem = orderItem.getMenuItem();
                                SelectedMenuDto selectedMenuDto = new SelectedMenuDto();
                                selectedMenuDto.setId(menuItem.getId());
                                selectedMenuDto.setTitle(menuItem.getName()); // Assuming "name" maps to "title" in UI
                                selectedMenuDto.setPrice(menuItem.getPrice());
                                selectedMenuDto.setQuantity(orderItem.getQuantity());
                                selectedMenuDto.setImage(menuItem.getImage());
                                return selectedMenuDto;
                            })
                            .collect(Collectors.toList());
                    orderDto.setMenuItemEntities(selectedMenuDtos);
                    orderDto.setUser(userDto);


                    return orderDto;
                })
                .collect(Collectors.toList());
    }




    @Override
    public List<String> allOrderNumbers() {
        return orderRepository.findAll().stream()
                .map(order -> order.getOrderReference()) // Directly map to String (order reference)
                .collect(Collectors.toList());
    }



    @Override
    public OrderDto getOrder(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
        OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);

        // Get Payment Method
        PaymentMethodEntity paymentMethod = orderEntity.getPaymentMethod();
        if (paymentMethod != null) {
            orderDto.setPaymentMethod(modelMapper.map(paymentMethod,PaymentMethodDto.class));
        }

        List<SelectedMenuDto> selectedMenuDtos = orderEntity.getOrderItems().stream()
                .map(orderItem -> {
                    MenuItemEntity menuItem = orderItem.getMenuItem();
                    SelectedMenuDto selectedMenuDto = new SelectedMenuDto();
                    selectedMenuDto.setId(menuItem.getId());
                    selectedMenuDto.setTitle(menuItem.getName()); // Assuming "name" maps to "title" in UI
                    selectedMenuDto.setPrice(menuItem.getPrice());
                    selectedMenuDto.setQuantity(orderItem.getQuantity());
                    selectedMenuDto.setImage(menuItem.getImage());
                    return selectedMenuDto;
                })
                .collect(Collectors.toList());
        orderDto.setMenuItemEntities(selectedMenuDtos);

        return orderDto;
    }

    @Override
    public List<OrderCreationDto> getOrdersByUserAndStatus(Long userId) {
        UserEntity user =userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("user was not found"));
        List<OrderEntity> orders = orderRepository.findByUserAndStatus(user, StatusType.PENDING.name());
        return orders.stream().map( order -> modelMapper.map(order,OrderCreationDto.class)).collect(Collectors.toList());
    }

    @Override
    public OrderCreationDto updateOrderStatus(Long orderId, OrderCreationDto status) {
         OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow( () -> new ResourceNotFoundException("Order was not found"));
         orderEntity.setStatus(status.getStatus());
         orderEntity.setOrderResponseTime(LocalDateTime.now());
         orderRepository.save(orderEntity);
         return modelMapper.map(orderEntity,OrderCreationDto.class);
    }


}
