package com.example.smartBar.smartBar.dto;

import com.example.smartBar.smartBar.domain.MenuItemEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long Id;
    private MenuItemDto menuItem;
    private Long quantity;


}
