package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.dto.UserDto;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.impl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {


    private AuthServiceImpl authService;

    @PostMapping("/customer-login-or-register")
     public ResponseEntity<ApiResponse<UserDto>> customerLoginOrRegister(@RequestBody UserDto userDto){
        UserDto userSaved = authService.CustomerRegisterOrLogin(userDto);
         return ResponseEntity.ok(ApiResponse.create("success",userSaved));
     }

}
