package com.example.smartBar.smartBar.services;

import com.example.smartBar.smartBar.dto.UserDto;

public interface AuthService {
    UserDto CustomerRegisterOrLogin(UserDto userDto);
}
