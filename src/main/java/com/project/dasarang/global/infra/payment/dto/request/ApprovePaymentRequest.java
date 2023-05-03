package com.project.dasarang.global.infra.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class ApprovePaymentRequest {

    private int amount;
    private String customerKey;
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private String customerMobilePhone;
    private int taxFreeAmount;
    private int cardInstallmentPlan;

}
