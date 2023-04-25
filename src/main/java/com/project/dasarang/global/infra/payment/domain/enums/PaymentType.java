package com.project.dasarang.global.infra.payment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {

    ONE("일회성 결제"),
    REGULAR("정기 결제");

    private final String name;

}
