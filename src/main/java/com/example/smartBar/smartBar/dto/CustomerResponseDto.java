package com.example.smartBar.smartBar.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private Long Id;
    private String name;
    private String phoneNumber;
    private String role;
    private String accessToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
