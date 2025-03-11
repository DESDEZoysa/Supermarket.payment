package com.eranga.supermarket.payment.service;

import com.eranga.supermarket.payment.model.dto.OrderDto;

public interface KafkaService {

    void consumeNewOrderEvent(String message);
    void sendFailOrderEvent(String message);
}
