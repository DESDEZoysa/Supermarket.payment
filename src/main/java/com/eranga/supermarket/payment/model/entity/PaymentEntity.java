package com.eranga.supermarket.payment.model.entity;

import com.eranga.supermarket.payment.model.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
    private Long id;
    @Version
    private Long version;
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "method", nullable = false)
    private PaymentMethodEnum method;
    @Column(name = "amount", nullable = false)
    private Double amount;
}
