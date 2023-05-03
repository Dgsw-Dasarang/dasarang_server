package com.project.dasarang.global.infra.payment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardType {

    CREDIT("신용카드"),
    CHECK("체크카드"),
    GIFT("기프트");

    private final String name;

}
