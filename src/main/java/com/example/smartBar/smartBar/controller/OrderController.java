package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.domain.UserEntity;
import com.example.smartBar.smartBar.dto.OrderCreationDto;
import com.example.smartBar.smartBar.dto.OrderDto;
import com.example.smartBar.smartBar.dto.OrderNumberDto;
import com.example.smartBar.smartBar.dto.UserDto;
import com.example.smartBar.smartBar.exception.ResourceNotFoundException;
import com.example.smartBar.smartBar.repository.UserRepository;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.OrderService;
import com.example.smartBar.smartBar.services.impl.OrderServiceImpl;
import com.example.smartBar.smartBar.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Tag(
        name="ORDERS REST API",
        description = "ORDER API"
)


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    Logger logger = LoggerFactory.getLogger(OrderController.class);



    @Operation(
            summary = "CREATE ORDER API",
            description = "Create order REST API and save into the db",
            security = @SecurityRequirement(name = "bearerAuth")

    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
//  @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/payment-method/{paymentMethodId}")
    public ResponseEntity<ApiResponse<OrderCreationDto>> makeOrder(@PathVariable Long paymentMethodId, @RequestBody   OrderDto orderDto){
        logger.info("Received OrderDto: {}", orderDto); // Log the orderDto
        if (orderDto == null) {
            logger.error("OrderDto is null");
            throw new ResourceNotFoundException("Order input is empty!");
        }
        OrderCreationDto response = orderService.makeOrder(orderDto,paymentMethodId);
        return new ResponseEntity<>(ApiResponse.create("success",response), HttpStatus.CREATED);
    }




    @Operation(
            summary = "READ - LIST ALL ORDERS REST API",
            description = "Show all users",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200"
    )

    @GetMapping()
    public ResponseEntity<ApiResponse<List<OrderDto>>>getOrders(){
        List<OrderDto>  allOrderList = orderService.allOrders();
        return new ResponseEntity<>(ApiResponse.create("list of orders",allOrderList), HttpStatus.OK);
    }


    @Operation(
            summary = "READ - GET ORDER BY USER REST API",
            description = "Show list of orders by user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<OrderCreationDto>>>ordersByUser(@PathVariable Long userId){
        return ResponseEntity.ok(ApiResponse.create("list of orders",orderService.getOrdersByUserAndStatus(userId)));
    }





    @Operation(
            summary = "READ -  GET ORDER REST API",
            description = "Show an order by Id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 CREATED"
    )
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDto>>getOrders(@PathVariable("id")  Long id){
        OrderDto  Order = orderService.getOrder(id);
        return new ResponseEntity<>(ApiResponse.create("your order",Order), HttpStatus.OK);
    }




    @Operation(
            summary = "READ - GET ALL ORDER NUMBERS API",
            description = "how all the order numbers that has not been processed",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 CREATED"
    )
    @GetMapping("/get-all-order-numbers")
    public ResponseEntity<ApiResponse<List<String>>>allOrderNumbers(){
        List<String> orderNumberDtos = orderService.allOrderNumbers();
        return new ResponseEntity<>(ApiResponse.create("List of order numbers",orderNumberDtos), HttpStatus.OK);
    }



    @Operation(
            summary = "UPDATE - ORDER's STATUS API",
            description = "update the orders status",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 CREATED"
    )
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderCreationDto>> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderCreationDto status){
        OrderCreationDto orderCreationDto = orderService.updateOrderStatus(orderId,status);
          return ResponseEntity.ok(ApiResponse.create("Order Status updated Successfully", orderCreationDto));
    }



}
