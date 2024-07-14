package com.example.smartBar.smartBar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private Boolean success;
    private Map<String, String> data;
    private String type;
    private LocalDateTime timeStamp;
}
