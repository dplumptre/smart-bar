package com.example.smartBar.smartBar.services;

import com.example.smartBar.smartBar.dto.CustomerResponseDto;
import com.example.smartBar.smartBar.dto.LoginDto;
import com.example.smartBar.smartBar.dto.UserAdminDto;
import com.example.smartBar.smartBar.dto.UserDto;

public interface AuthService {
    CustomerResponseDto CustomerRegisterOrLogin(UserDto userDto);

    public String AdminRegister(UserAdminDto registerDto);

    public String AdminLogin(LoginDto loginDto);
}
