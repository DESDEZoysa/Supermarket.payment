package com.eranga.supermarket.payment.model.dto;

import com.eranga.supermarket.payment.model.enums.PaymentMethodEnum;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long id;
    private Long orderId;
    private PaymentMethodEnum method;
    private Double amount;
}
