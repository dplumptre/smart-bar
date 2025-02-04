package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.domain.PaymentMethodEntity;
import com.example.smartBar.smartBar.dto.PaymentMethodDto;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment-methods")
@AllArgsConstructor
public class PaymentMethodController {

    private PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentMethodDto>> createPaymentMethod(@RequestBody  PaymentMethodDto paymentMethodDto){
        PaymentMethodDto savedPayment = paymentMethodService.createPaymentMethod(paymentMethodDto);
        return new ResponseEntity<>(ApiResponse.create("success",savedPayment), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentMethodDto>>>getPaymentMethod(){
        return ResponseEntity.ok(ApiResponse.create("list of payment methods",paymentMethodService.getPaymentMethod()));
    }



}
