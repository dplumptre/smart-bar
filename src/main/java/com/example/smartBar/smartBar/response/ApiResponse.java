package com.example.smartBar.smartBar.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> create(String message, T data) {
        return new ApiResponse<>( true,message,data );
    }
    public static <T> ApiResponse<T> create(Boolean success,String message, T data) {
        return new ApiResponse<>( success,message,data );
    }
}


