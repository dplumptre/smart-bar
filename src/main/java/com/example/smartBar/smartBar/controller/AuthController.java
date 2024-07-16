package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.dto.CustomerResponseDto;
import com.example.smartBar.smartBar.dto.JwtAuthResponse;
import com.example.smartBar.smartBar.dto.LoginDto;
import com.example.smartBar.smartBar.dto.UserDto;
import com.example.smartBar.smartBar.exception.ErrorDetail;
import com.example.smartBar.smartBar.exception.ValidationError;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {


    private AuthServiceImpl authService;

    @PostMapping("/auth/customer-login-or-register")
     public ResponseEntity<ApiResponse<CustomerResponseDto>> customerLoginOrRegister(@RequestBody @Valid UserDto userDto){
        CustomerResponseDto userSaved = authService.CustomerRegisterOrLogin(userDto);
         return ResponseEntity.ok(ApiResponse.create("success",userSaved));
     }

    @PostMapping("/auth/admin-login")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> AdminLogin(@RequestBody @Valid LoginDto loginDto){
        String token = authService.AdminLogin(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        ApiResponse<JwtAuthResponse> response = ApiResponse.create("user logged in successfully",jwtAuthResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/admin-area/register-admin")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody @Valid UserDto userDto){
        String response = authService.AdminRegister(userDto);
        ApiResponse<String> finalResponse = ApiResponse.create("success",response);
        return new ResponseEntity<>(finalResponse, HttpStatus.CREATED);
    }







    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ValidationError errorDetails = new ValidationError(
                false,
                errors,
                "validation error",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorDetails, ex.getStatusCode());

    }

}
