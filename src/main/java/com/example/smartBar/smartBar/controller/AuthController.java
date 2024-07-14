package com.example.smartBar.smartBar.controller;


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
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {


    private AuthServiceImpl authService;

    @PostMapping("/customer-login-or-register")
     public ResponseEntity<ApiResponse<UserDto>> customerLoginOrRegister(@RequestBody @Valid UserDto userDto){
        UserDto userSaved = authService.CustomerRegisterOrLogin(userDto);
         return ResponseEntity.ok(ApiResponse.create("success",userSaved));
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
