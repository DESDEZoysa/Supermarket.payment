package com.eranga.supermarket.payment.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long orderId;
    private Integer batchId;
    private Integer quantity;
    private Double discount;
}
