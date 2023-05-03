package com.project.dasarang.global.infra.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
public class CashReceiptsResponse implements Serializable {

    private String receiptKey;
    private String orderId;
    private String orderName;
    private String type;
    private String issueNumber;
    private String receiptUrl;
    private String businessNumber;
    private String transactionType;
    private Integer amount;
    private Integer taxFreeAmount;
    private String issueStatus;
    private FailureResponse failure;
    private String customerIdentityNumber;
    private String requestedAt;

}
