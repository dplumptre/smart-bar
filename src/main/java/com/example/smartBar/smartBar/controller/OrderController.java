package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.dto.OrderDto;
import com.example.smartBar.smartBar.dto.OrderItemDto;
import com.example.smartBar.smartBar.exception.ResourceNotFoundException;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.OrderService;
import com.example.smartBar.smartBar.services.impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    OrderServiceImpl orderService;
    Logger logger = LoggerFactory.getLogger(OrderController.class);
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }



    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> makeOrder(@RequestBody OrderDto orderDto){
        logger.info("Received OrderDto: {}", orderDto); // Log the orderDto
        if (orderDto == null) {
            logger.error("OrderDto is null");
            throw new ResourceNotFoundException("Order input is empty!");
        }
        String response = orderService.makeOrder(orderDto);
        return new ResponseEntity<>(ApiResponse.create("success",response), HttpStatus.CREATED);
    }

}
