package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.dto.*;
import com.example.smartBar.smartBar.exception.ErrorDetail;
import com.example.smartBar.smartBar.exception.ValidationError;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
        name="AUTH REST API",
        description = "Authentication & Registration"
)

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {


    private AuthServiceImpl authService;

    @GetMapping("/auth")
    public ResponseEntity<ApiResponse<String>> welcome(){
        return ResponseEntity.ok(ApiResponse.create("success","I am here"));
    }


    @Operation(
            summary = "CREATE - API",
            description = "Create / Register a user"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201"
    )
    @PostMapping("/auth/customer-login-or-register")
     public ResponseEntity<ApiResponse<CustomerResponseDto>> customerLoginOrRegister(@RequestBody @Valid UserDto userDto){
        CustomerResponseDto userSaved = authService.CustomerRegisterOrLogin(userDto);
         return ResponseEntity.ok(ApiResponse.create("success",userSaved));
     }


    @Operation(
            summary = "ADMIN REST API",
            description = "Admin login"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 200"
    )
    @PostMapping("/auth/admin-login")
    public ResponseEntity<ApiResponse<JwtAuthResponse>> AdminLogin(@RequestBody @Valid LoginDto loginDto){
        String token = authService.AdminLogin(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        ApiResponse<JwtAuthResponse> response = ApiResponse.create("user logged in successfully",jwtAuthResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "ADMIN REGISTER REST API",
            description = "Create an Admin account"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201"
    )
    @PostMapping("/admin-area/register-admin")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody @Valid UserAdminDto userAdminDto){
        String response = authService.AdminRegister(userAdminDto);
        ApiResponse<String> finalResponse = ApiResponse.create("success",response);
        return new ResponseEntity<>(finalResponse, HttpStatus.CREATED);
    }









}
