package com.example.smartBar.smartBar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetail> handleAuthException(AuthException exception,
                                                               WebRequest webRequest) {

        ErrorDetail errorDetails = new ErrorDetail(
                false,
                exception.getMessage(),
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDetails, exception.getHttpStatus());
    }










}








