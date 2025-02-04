package com.example.smartBar.smartBar.services.impl;

import com.example.smartBar.smartBar.domain.PaymentMethodEntity;
import com.example.smartBar.smartBar.dto.MenuItemDto;
import com.example.smartBar.smartBar.dto.PaymentMethodDto;
import com.example.smartBar.smartBar.repository.PaymentMethodRepository;
import com.example.smartBar.smartBar.services.PaymentMethodService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;
    private ModelMapper modelMapper;
    @Override
    public  PaymentMethodDto createPaymentMethod(PaymentMethodDto paymentMethodDto) {
            PaymentMethodEntity newPaymentMethod = modelMapper.map(paymentMethodDto,PaymentMethodEntity.class);
            paymentMethodRepository.save(newPaymentMethod);
            return modelMapper.map(newPaymentMethod,PaymentMethodDto.class);
    }

    @Override
    public List<PaymentMethodDto> getPaymentMethod() {
        return paymentMethodRepository.findAll().stream().map( paymentMethod -> modelMapper.map(paymentMethod,PaymentMethodDto.class)).collect(Collectors.toList());
    }





}
