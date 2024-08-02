package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.domain.UserEntity;
import com.example.smartBar.smartBar.dto.MenuItemDto;
import com.example.smartBar.smartBar.dto.OrderCreationDto;
import com.example.smartBar.smartBar.dto.OrderDto;
import com.example.smartBar.smartBar.dto.UserDto;
import com.example.smartBar.smartBar.exception.ResourceNotFoundException;
import com.example.smartBar.smartBar.exception.ValidationError;
import com.example.smartBar.smartBar.repository.UserRepository;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.impl.OrderServiceImpl;
import com.example.smartBar.smartBar.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Tag(
        name="ORDERS REST API",
        description = "ORDER API"
)


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    OrderServiceImpl orderService;
    Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public OrderController(OrderServiceImpl orderService, UserRepository userRepository, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Operation(
            summary = "CREATE ORDER API",
            description = "Create order REST API and save into the db"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> makeOrder(@RequestBody  @Valid OrderDto orderDto){
        logger.info("Received OrderDto: {}", orderDto); // Log the orderDto
        if (orderDto == null) {
            logger.error("OrderDto is null");
            throw new ResourceNotFoundException("Order input is empty!");
        }
        String response = orderService.makeOrder(orderDto);
        return new ResponseEntity<>(ApiResponse.create("success",response), HttpStatus.CREATED);
    }

    @Operation(
            summary = "READ - USER REST API",
            description = "Show a user by phone number"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 CREATED"
    )

    @GetMapping("/user")
    public ResponseEntity<UserDto> showUser(){

        UserEntity user = userRepository.findByPhoneNumber(SecurityUtil.getCurrentUsername()).orElseThrow( ()-> new ResourceNotFoundException(" not found!"));
                return ResponseEntity.ok(modelMapper.map(user,UserDto.class));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<OrderCreationDto>>>getOrders(){
        List<OrderCreationDto>  allOrderList = orderService.allOrders();
        return new ResponseEntity<>(ApiResponse.create("list of orders",allOrderList), HttpStatus.OK);

    }





}
