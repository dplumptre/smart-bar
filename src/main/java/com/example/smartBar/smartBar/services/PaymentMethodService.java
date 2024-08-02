package com.example.smartBar.smartBar.services;

import com.example.smartBar.smartBar.dto.PaymentMethodDto;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethodDto createPaymentMethod(PaymentMethodDto paymentMethodDto);

    List<PaymentMethodDto> getPaymentMethod();

}
