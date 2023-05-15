package com.project.dasarang.global.infra.payment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CancelResponse implements Serializable {

    private Number cancelAmount;
    private String cancelReason;
    private Number taxFreeAmount;
    private Integer taxExemptionAmount;
    private Number refundableAmount;
    private Number easyPayDiscountAmount;
    private String canceledAt;
    private String transactionKey;
    private String receiptKey;

}
