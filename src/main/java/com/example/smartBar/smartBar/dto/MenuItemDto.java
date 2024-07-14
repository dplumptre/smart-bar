package com.example.smartBar.smartBar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto {
Long Id;
String name;
double price;
String image;
LocalDateTime createdAt;
LocalDateTime updatedAt;
}
