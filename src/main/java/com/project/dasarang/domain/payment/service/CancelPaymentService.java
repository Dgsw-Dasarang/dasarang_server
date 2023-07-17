package com.project.dasarang.domain.payment.service;

import com.project.dasarang.domain.payment.presentation.dto.reqeust.CancelPaymentRequest;
import com.project.dasarang.domain.payment.presentation.dto.response.CancelPaymentResponse;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserStatus;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.infra.payment.domain.Payment;
import com.project.dasarang.global.infra.payment.domain.repository.CardRepository;
import com.project.dasarang.global.infra.payment.domain.repository.PaymentRepository;
import com.project.dasarang.global.infra.payment.dto.response.CancelPaymentReturnResponse;
import com.project.dasarang.global.infra.payment.exception.PaymentNotFoundException;
import com.project.dasarang.global.infra.payment.service.TossService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelPaymentService {

    private final UserFacade userFacade;
    private final TossService tossService;
    private final PaymentRepository paymentRepository;
    private final CardRepository cardRepository;

    @Transactional
    public CancelPaymentResponse execute(CancelPaymentRequest request) {
        User user = userFacade.getCurrentUser();

        Payment payment = paymentRepository.findByUserAndCustomerKey(user, user.getCustomerKey())
                .orElseThrow(() -> PaymentNotFoundException.EXCEPTION);

        CancelPaymentReturnResponse response = tossService.paymentCancel(request, payment.getPaymentKey());

        user.setStatus(UserStatus.DEACTIVATED);
        user.setCustomerKey(null);
        cardRepository.delete(payment.getCardList().get(0));
        paymentRepository.delete(payment);

        return CancelPaymentResponse.of(response);
    }

}
