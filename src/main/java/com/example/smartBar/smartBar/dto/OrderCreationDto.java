package com.example.smartBar.smartBar.dto;

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
    private Long userId;
    private String orderReference;
    private Double totalPrice;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
