package com.example.smartBar.smartBar.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @Valid
    @NotBlank(message = "Please provide a phone number is mandatory")
    @NotNull(message = "Please provide a phone number")
    private String phoneNumber;
    @Valid
    @NotBlank(message = "Please provide a Password is mandatory")
    @NotNull(message = "Please provide a password")
    private String password;
}
