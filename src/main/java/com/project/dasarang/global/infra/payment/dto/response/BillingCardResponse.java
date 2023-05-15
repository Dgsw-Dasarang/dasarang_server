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
public class BillingCardResponse implements Serializable {

    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String cardType;
    private String ownerType;

}
