package com.eranga.supermarket.payment.controller;

import com.eranga.supermarket.payment.model.dto.PaymentDto;
import com.eranga.supermarket.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentDto create(@RequestBody PaymentDto paymentDto){
        return paymentService.create(paymentDto);
    }
}
