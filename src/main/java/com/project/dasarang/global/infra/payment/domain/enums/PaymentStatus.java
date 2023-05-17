package com.project.dasarang.global.infra.payment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    PAYMENT("결제"),
    CANCEL("취소");

    private final String name;

}
