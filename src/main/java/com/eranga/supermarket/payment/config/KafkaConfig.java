package com.eranga.supermarket.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic.fail_order}")
    private String failOrderTopic;

    @Bean
    public NewTopic failOrderTopic(){
        return new NewTopic(failOrderTopic,2,(short) 2);
    }
}
