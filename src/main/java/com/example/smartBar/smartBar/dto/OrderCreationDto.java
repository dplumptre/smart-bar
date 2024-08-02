package com.example.smartBar.smartBar.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationDto {

    private Long Id;
    private UserDto userId;
    private String orderReference;
    @NotNull(message = "Payment method is mandatory")
    @Positive(message = "Payment method ID must be positive")
    private PaymentMethodDto paymentMethodId;
    private Double totalPrice;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
