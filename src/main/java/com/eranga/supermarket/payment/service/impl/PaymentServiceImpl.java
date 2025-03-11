package com.eranga.supermarket.payment.service.impl;

import com.eranga.supermarket.payment.model.dto.OrderDto;
import com.eranga.supermarket.payment.model.dto.PaymentDto;
import com.eranga.supermarket.payment.model.entity.PaymentEntity;
import com.eranga.supermarket.payment.model.mapper.PaymentMapper;
import com.eranga.supermarket.payment.repository.PaymentRepository;
import com.eranga.supermarket.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDto create(PaymentDto paymentDto) {
        PaymentEntity paymentEntity = paymentRepository.save(paymentMapper.paymentDtoToEntity(paymentDto));
        return paymentMapper.paymentEntityToDto(paymentEntity);
    }

    @Override
    public PaymentDto create(OrderDto orderDto) {
        return create(paymentMapper.orderDtoToPaymentDto(orderDto));
    }
}
