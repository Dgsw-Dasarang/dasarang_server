package com.project.dasarang.global.infra.payment.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.config.WebClientConfiguration;
import com.project.dasarang.global.infra.payment.TossProperties;
import com.project.dasarang.global.infra.payment.dto.request.ApprovePaymentRequest;
import com.project.dasarang.domain.payment.presentation.dto.reqeust.IssueBillingRequest;
import com.project.dasarang.global.infra.payment.dto.response.BillingResponse;
import com.project.dasarang.global.infra.payment.dto.response.PaymentResponse;
import com.project.dasarang.global.infra.payment.dto.response.PaymentReturnResponse;
import com.project.dasarang.global.infra.payment.exception.PaymentForbiddenException;
import com.project.dasarang.global.infra.payment.exception.PaymentInfoException;
import com.project.dasarang.global.infra.payment.exception.PaymentServerException;
import com.project.dasarang.global.infra.payment.exception.PaymentUnauthorizedKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class TossService {

    private final TossProperties tossProperties;
    private final WebClientConfiguration webClient;

    public PaymentReturnResponse payment(IssueBillingRequest request, User user) {
        BillingResponse response = getBillingKey(request).block();
        assert response != null;

        ApprovePaymentRequest approveRequest = ApprovePaymentRequest.builder()
                .amount(1000).customerKey(response.getCustomerKey())
                .orderId(createOrder()).orderName("다사랑")
                .customerEmail(user.getEmail()).customerName(user.getUserId())
                .customerMobilePhone(user.getNumber())
                .taxFreeAmount(0).cardInstallmentPlan(0)
                .build();

        log.info(response.getBillingKey());
        PaymentResponse approveResponse = approvePayment(response.getBillingKey(), approveRequest).block();

        assert approveResponse != null;
        return PaymentReturnResponse.of(approveResponse, request.getCustomerKey());
    }

    public Mono<PaymentResponse> approvePayment(String billingKey, ApprovePaymentRequest request) {
        return webClient.tossClient()
                .post()
                .uri("/v1/billing/" + billingKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                        throw PaymentInfoException.EXCEPTION;
                    } else if(clientResponse.statusCode().equals(HttpStatus.UNAUTHORIZED)) {
                        throw PaymentUnauthorizedKeyException.EXCEPTION;
                    } else if(clientResponse.statusCode().equals(HttpStatus.FORBIDDEN)) {
                        throw PaymentForbiddenException.EXCEPTION;
                    } else if(clientResponse.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                        throw PaymentServerException.EXCEPTION;
                    }

                    return clientResponse.bodyToMono(PaymentResponse.class);
                });
    }

    public void canclePayment() {
        // TODO 정기결제 취소
    }

    public Mono<BillingResponse> getBillingKey(IssueBillingRequest request) {
        return webClient.tossClient()
                .post()
                .uri("/v1/billing/authorizations/issue")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchangeToMono(clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                        throw PaymentInfoException.EXCEPTION;
                    } else if(clientResponse.statusCode().equals(HttpStatus.FORBIDDEN)) {
                        throw PaymentForbiddenException.EXCEPTION;
                    } else if(clientResponse.statusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                        throw PaymentServerException.EXCEPTION;
                    }

                    return clientResponse.bodyToMono(BillingResponse.class);
                });
    }

    public String createOrder() {
        Random random = new Random();

        return random.ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(40)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
