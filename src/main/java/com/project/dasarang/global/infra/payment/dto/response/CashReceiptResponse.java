package com.project.dasarang.global.infra.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
public class CashReceiptResponse implements Serializable {

    private String type;
    private String receiptKey;
    private String issueNumber;
    private String receiptUrl;
    private Number amount;
    private Number taxFreeAmount;

}
