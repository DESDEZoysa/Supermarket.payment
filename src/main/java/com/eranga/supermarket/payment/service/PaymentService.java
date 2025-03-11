package com.eranga.supermarket.payment.service;

import com.eranga.supermarket.payment.model.dto.OrderDto;
import com.eranga.supermarket.payment.model.dto.PaymentDto;

public interface PaymentService {

    PaymentDto create(PaymentDto paymentDto);
    PaymentDto create(OrderDto orderDto);
}
