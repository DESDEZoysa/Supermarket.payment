package com.eranga.supermarket.payment.repository;

import com.eranga.supermarket.payment.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
