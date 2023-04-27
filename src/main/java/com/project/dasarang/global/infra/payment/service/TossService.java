package com.project.dasarang.global.infra.payment.service;

import com.project.dasarang.global.infra.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TossService {

    private final PaymentRepository paymentRepository;

}
