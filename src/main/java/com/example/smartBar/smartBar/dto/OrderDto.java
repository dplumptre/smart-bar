package com.example.smartBar.smartBar.dto;

import com.example.smartBar.smartBar.domain.MenuItemEntity;
import com.example.smartBar.smartBar.domain.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private List<MenuItemEntity> menuItemEntities;
    private Double totalPrice;
}
