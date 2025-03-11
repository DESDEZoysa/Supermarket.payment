package com.eranga.supermarket.payment.service.impl;

import com.eranga.supermarket.payment.model.dto.OrderDto;
import com.eranga.supermarket.payment.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.payment.service.KafkaService;
import com.eranga.supermarket.payment.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final PaymentService paymentService;
    private final KafkaTemplate<String, OrderDto> kafkaOrderTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);
    @Value("${spring.kafka.topic.fail_order}")
    private String failOrderTopic;

    @Override
    @RetryableTopic(
            attempts = "${spring.kafka.consumer.attempts}",
            backoff = @Backoff(delay = 6000, multiplier = 2,maxDelay = 100000)
    )
    @KafkaListener(topics = "${spring.kafka.topic.new_order}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeNewOrderEvent(String message) {
        logger.info("Receive new order event = {}", message);
        try {
            paymentService.create(new ObjectMapper().readValue(message, OrderDto.class));
        }catch (Exception e){
            logger.error("Error while processing new order event {}", message);
            throw new RuntimeException();
        }

    }

    @Override
    public void sendFailOrderEvent(String message) {
        try{
            OrderDto orderDto = new ObjectMapper().readValue(message, OrderDto.class);
            orderDto.setFailReason(OrderFailReasonEnum.PaymentProcessFail);
            CompletableFuture<SendResult<String, OrderDto>> completableFuture = kafkaOrderTemplate.send(failOrderTopic, orderDto);
            completableFuture.whenComplete((result, ex) -> {
                if (ex == null) {
                    logger.info("Sent fail order event = {}", orderDto);
                } else {
                    logger.error("Unable to send fail order event = {}", orderDto);
                }
            });
        }catch (Exception e){
            logger.error("Unable to send fail order event = {} because of {}", message, e.getMessage());
        }
    }

    @DltHandler
    private void consumeNewOrderEventDLT(String message) {
        logger.info("Send new order event to dead letter topic= {}", message);
    }
}
