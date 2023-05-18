package com.project.dasarang.domain.payment.service;

import com.project.dasarang.domain.payment.presentation.dto.reqeust.CancelPaymentRequest;
import com.project.dasarang.domain.payment.presentation.dto.response.CancelPaymentResponse;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserStatus;
import com.project.dasarang.domain.user.domain.repository.UserRepository;
import com.project.dasarang.domain.user.exception.UserAlreadyActiveException;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.infra.payment.domain.Card;
import com.project.dasarang.global.infra.payment.domain.Payment;
import com.project.dasarang.global.infra.payment.domain.enums.PaymentStatus;
import com.project.dasarang.global.infra.payment.domain.repository.CardRepository;
import com.project.dasarang.global.infra.payment.domain.repository.PaymentRepository;
import com.project.dasarang.domain.payment.presentation.dto.reqeust.IssueBillingRequest;
import com.project.dasarang.global.infra.payment.dto.response.CancelPaymentReturnResponse;
import com.project.dasarang.global.infra.payment.dto.response.PaymentReturnResponse;
import com.project.dasarang.global.infra.payment.exception.CardAlreadyExistsException;
import com.project.dasarang.global.infra.payment.exception.PaymentAlreadyExistsException;
import com.project.dasarang.global.infra.payment.exception.PaymentNotFoundException;
import com.project.dasarang.global.infra.payment.service.TossService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final CardRepository cardRepository;
    private final TossService tossService;

    @Transactional
    public void payment(IssueBillingRequest request) {
        User user = userFacade.getCurrentUser();

        if(user.getStatus().equals(UserStatus.ACTIVE))
            throw UserAlreadyActiveException.EXCEPTION;

        PaymentReturnResponse response = tossService.payment(request, user);

        cardRepository.findByNumber(response.getCard().getNumber())
                .ifPresent(m -> {
                    throw CardAlreadyExistsException.EXCEPTION;
                });

        paymentRepository.findByPaymentKey(response.getPaymentKey())
                .ifPresent(m -> {
                    throw PaymentAlreadyExistsException.EXCEPTION;
                });

        Card card = response.getCard().toEntity();
        Payment payment = response.toEntity();

        user.setCustomerKey(payment.getCustomerKey());
        user.setStatus(UserStatus.ACTIVE);
        payment.setUser(user);
        payment.addCard(card);
        paymentRepository.save(payment);
    }

    @Transactional
    public CancelPaymentResponse cancelPayment(CancelPaymentRequest request) {
        User user = userFacade.getCurrentUser();

        Payment payment = paymentRepository.findByUserAndCustomerKey(user, user.getCustomerKey())
                .orElseThrow(() -> PaymentNotFoundException.EXCEPTION);

        CancelPaymentReturnResponse response = tossService.paymentCancel(request, payment.getPaymentKey());

        user.setStatus(UserStatus.DEACTIVATED);
        payment.setStatus(PaymentStatus.CANCEL);


        return CancelPaymentResponse.of(response);
    }

}
