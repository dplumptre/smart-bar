package com.example.smartBar.smartBar.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
}
