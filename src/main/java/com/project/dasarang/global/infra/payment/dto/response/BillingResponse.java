package com.project.dasarang.global.infra.payment.dto.response;

import com.project.dasarang.global.infra.payment.dto.response.BillingCardResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
public class BillingResponse implements Serializable {

    private String mId;
    private String customerKey;
    private String authenticatedAt;
    private String method;
    private String billingKey;
    private BillingCardResponse card;

}
