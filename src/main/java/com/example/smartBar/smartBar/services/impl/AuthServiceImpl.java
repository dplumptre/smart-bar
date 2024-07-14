package com.example.smartBar.smartBar.services.impl;

import com.example.smartBar.smartBar.domain.UserEntity;
import com.example.smartBar.smartBar.dto.UserDto;
import com.example.smartBar.smartBar.exception.AuthException;
import com.example.smartBar.smartBar.exception.ResourceNotFoundException;
import com.example.smartBar.smartBar.repository.AuthRepository;
import com.example.smartBar.smartBar.services.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDto CustomerRegisterOrLogin(UserDto userDto) {

        if(userDto.getPhoneNumber() == null){
            throw new AuthException(HttpStatus.BAD_REQUEST,"Phone Number cannot be empty!");
        }

        UserEntity customer = authRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if(customer == null) {
           //register customer
           userDto.setRole("CUSTOMER");
           UserEntity newUser = modelMapper.map(userDto, UserEntity.class);
           authRepository.save(newUser);
           return  modelMapper.map( newUser, UserDto.class);
       }

       //log customer in

        return    modelMapper.map( customer, UserDto.class);

    }
}
