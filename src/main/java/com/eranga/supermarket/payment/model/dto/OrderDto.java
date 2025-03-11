package com.eranga.supermarket.payment.model.dto;

import com.eranga.supermarket.payment.model.enums.OrderFailReasonEnum;
import com.eranga.supermarket.payment.model.enums.OrderStatusEnum;
import com.eranga.supermarket.payment.model.enums.PaymentMethodEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Integer customerId;
    private String orderDate;
    private OrderStatusEnum status;
    private OrderFailReasonEnum failReason;
    private PaymentMethodEnum method;
    private Double amount;
    private List<OrderItemDto> orderItemDtoList;
}
