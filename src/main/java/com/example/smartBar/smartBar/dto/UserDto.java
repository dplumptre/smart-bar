package com.example.smartBar.smartBar.dto;


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
public class UserDto {
    private Long Id;
    @Valid
    @NotBlank(message = "Please provide a name is mandatory")
    @NotNull(message = "Please provide a name")
    private String name;
    @Valid
    @NotBlank(message = "Please provide a phone number is mandatory")
    @NotNull(message = "Please provide a phone number")
    private String phoneNumber;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
