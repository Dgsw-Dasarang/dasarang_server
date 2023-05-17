package com.project.dasarang.domain.payment.presentation.dto.response;

import com.project.dasarang.global.infra.payment.dto.response.CancelPaymentReturnResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class CancelPaymentResponse {

    private int cancelAmount;
    private String cancelReason;

    public static CancelPaymentResponse of(CancelPaymentReturnResponse payment) {
        return CancelPaymentResponse.builder()
                .cancelAmount(payment.getCancelAmount())
                .cancelReason(payment.getCancelReason())
                .build();
    }

}
