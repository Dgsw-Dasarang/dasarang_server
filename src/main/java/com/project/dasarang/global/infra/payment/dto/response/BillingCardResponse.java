package com.project.dasarang.global.infra.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
public class BillingCardResponse implements Serializable {

    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String cardType;
    private String ownerType;

}
