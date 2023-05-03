package com.project.dasarang.global.infra.payment.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssueBillingRequest {

    private String authKey;
    private String customerKey;

}
