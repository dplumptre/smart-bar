package com.example.smartBar.smartBar.services.impl;

import com.example.smartBar.smartBar.enums.UserType;
import com.example.smartBar.smartBar.domain.UserEntity;
import com.example.smartBar.smartBar.dto.CustomerResponseDto;
import com.example.smartBar.smartBar.dto.LoginDto;
import com.example.smartBar.smartBar.dto.UserDto;
import com.example.smartBar.smartBar.exception.AuthException;
import com.example.smartBar.smartBar.repository.UserRepository;
import com.example.smartBar.smartBar.security.JwtTokenProvider;
import com.example.smartBar.smartBar.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Value("${customer.passKey}")
    private String passKey;


    @Override
    public CustomerResponseDto CustomerRegisterOrLogin(UserDto userDto) {
        UserEntity customer = userRepository.findByPhoneNumber(userDto.getPhoneNumber())
                .orElseGet(() -> createUserFromDto(userDto));

        Authentication authentication  = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
               customer.getPhoneNumber(),
               passKey
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        CustomerResponseDto newCustomer =  new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getRole(),
                token,
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );


        return    modelMapper.map( newCustomer, CustomerResponseDto.class);
    }



    private UserEntity createUserFromDto(UserDto userDto) {
        userDto.setRole(UserType.CUSTOMER.name());
        userDto.setPassword(passwordEncoder.encode(passKey));
        UserEntity newUser = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(newUser);
        return  newUser;
    }



    // ADMIN

    @Override
    public String AdminLogin(LoginDto loginDto) {
        Authentication authentication  = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                loginDto.getPhoneNumber(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String AdminRegister(UserDto registerDto) {
        if(userRepository.existsByPhoneNumber(registerDto.getPhoneNumber())){
            throw  new AuthException(HttpStatus.BAD_REQUEST, "username already exist");
        }
        UserEntity  user = new UserEntity();
        user.setName(registerDto.getName());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setRole(UserType.ADMIN.name());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);
        return "Admin was registered successfully!";
    }

}
