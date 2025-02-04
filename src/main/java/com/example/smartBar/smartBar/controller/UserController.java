package com.example.smartBar.smartBar.controller;

import com.example.smartBar.smartBar.domain.UserEntity;
import com.example.smartBar.smartBar.dto.UserDto;
import com.example.smartBar.smartBar.exception.ResourceNotFoundException;
import com.example.smartBar.smartBar.repository.UserRepository;
import com.example.smartBar.smartBar.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {


    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Operation(
            summary = "READ - DISPLAY USER REST API",
            description = "Show a user by phone number",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )

    @GetMapping("/user")
    public ResponseEntity<UserDto> showUser(){

        UserEntity user = userRepository.findByPhoneNumber(SecurityUtil.getCurrentUsername()).orElseThrow( ()-> new ResourceNotFoundException(" not found!"));
        return ResponseEntity.ok(modelMapper.map(user,UserDto.class));
    }
}
