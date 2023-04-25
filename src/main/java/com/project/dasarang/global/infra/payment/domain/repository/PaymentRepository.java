package com.project.dasarang.global.infra.payment.domain.repository;

import com.project.dasarang.global.infra.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
