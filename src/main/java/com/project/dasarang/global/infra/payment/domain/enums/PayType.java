package com.project.dasarang.global.infra.payment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayType {

    CREDIT_CARD("신용카드"),
    CHECK_CARD("체크카드");

    private final String name;

}
