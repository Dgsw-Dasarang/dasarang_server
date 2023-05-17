package com.project.dasarang.global.infra.payment.domain.repository;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.infra.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentKey(String paymentKey);

    Optional<Payment> findByUserAndCustomerKey(User user, String customerKey);

}
