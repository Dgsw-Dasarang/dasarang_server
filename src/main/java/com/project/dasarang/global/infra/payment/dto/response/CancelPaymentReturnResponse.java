package com.project.dasarang.global.infra.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class CancelPaymentReturnResponse {

    private int cancelAmount;
    private String cancelReason;
    private String canceledAt;

    public static CancelPaymentReturnResponse of(PaymentResponse payment) {
        return CancelPaymentReturnResponse.builder()
                .cancelAmount(payment.getCancels()[0].getCancelAmount().intValue())
                .cancelReason(payment.getCancels()[0].getCancelReason())
                .canceledAt(payment.getCancels()[0].getCanceledAt())
                .build();
    }

}
