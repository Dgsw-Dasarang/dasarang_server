package com.project.dasarang.domain.payment.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserStatus;
import com.project.dasarang.domain.user.exception.UserAlreadyActiveException;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.infra.payment.domain.Card;
import com.project.dasarang.global.infra.payment.domain.Payment;
import com.project.dasarang.global.infra.payment.domain.repository.PaymentRepository;
import com.project.dasarang.domain.payment.presentation.dto.reqeust.IssueBillingRequest;
import com.project.dasarang.global.infra.payment.dto.response.PaymentReturnResponse;
import com.project.dasarang.global.infra.payment.service.TossService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserFacade userFacade;
    private final PaymentRepository paymentRepository;
    private final TossService tossService;

    @Transactional
    public void payment(IssueBillingRequest request) {
        User user = userFacade.getCurrentUser();

        if(user.getStatus().equals(UserStatus.ACTIVE))
            throw UserAlreadyActiveException.EXCEPTION;

        PaymentReturnResponse response = tossService.payment(request, user);

        Card card = response.getCard().toEntity();
        Payment payment = response.toEntity();

        log.info(card.getNumber());
        user.setStatus(UserStatus.ACTIVE);
        card.setPayment(payment);
        payment.addCard(card);
        paymentRepository.save(payment);
    }

}
