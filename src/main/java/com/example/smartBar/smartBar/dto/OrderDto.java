package com.example.smartBar.smartBar.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private UserDto user;
    private List<SelectedMenuDto> menuItemEntities;
    private String orderReference;
    private Double totalPrice;
    private PaymentMethodDto paymentMethods;
}
