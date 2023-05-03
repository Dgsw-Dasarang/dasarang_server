package com.project.dasarang.global.infra.payment.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OwnerType {

    PERSONAL("개인"),
    CORPORATION("법인");

    private final String name;

}
