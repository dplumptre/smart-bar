package com.example.smartBar.smartBar.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.awt.*;


@Data
public class OrderItemDto {

    private Long Id;
    private MenuItem menuItem;
    private Long quantity;


}
