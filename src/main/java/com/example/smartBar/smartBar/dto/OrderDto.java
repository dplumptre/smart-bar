package com.example.smartBar.smartBar.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long Id;
    private UserDto user;
    private List<SelectedMenuDto> menuItemEntities;
    private String orderReference;
    private String status;
    private LocalDateTime orderResponseTime;
    private Double totalPrice;
    private PaymentMethodDto paymentMethod;
}


