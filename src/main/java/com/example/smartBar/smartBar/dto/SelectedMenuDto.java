package com.example.smartBar.smartBar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedMenuDto {

    private Long Id;
    private String title;
    private double price;
    private Long quantity;
    private String image;
}
