package com.eranga.supermarket.payment.model.mapper;

import com.eranga.supermarket.payment.model.dto.OrderDto;
import com.eranga.supermarket.payment.model.dto.PaymentDto;
import com.eranga.supermarket.payment.model.entity.PaymentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentEntity paymentDtoToEntity(PaymentDto paymentDto){
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(paymentDto,paymentEntity);
        return paymentEntity;
    }

    public PaymentDto paymentEntityToDto(PaymentEntity paymentEntity){
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(paymentEntity,paymentDto);
        return paymentDto;
    }

    public PaymentDto orderDtoToPaymentDto(OrderDto orderDto){
        return PaymentDto.builder()
                .orderId(orderDto.getId())
                .method(orderDto.getMethod())
                .amount(orderDto.getAmount())
                .build();
    }
}
