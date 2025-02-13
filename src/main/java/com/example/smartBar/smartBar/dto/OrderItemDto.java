package com.example.smartBar.smartBar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long Id;
    private double price;
    private Long quantity;
    private Long orderId;
    private Long menuItemId;


}
