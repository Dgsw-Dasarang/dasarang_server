package com.project.dasarang.domain.education.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EducationCategory {

    NUMBER("acaAsnum"),
    ZONE_NAME("admstZoneName"),
    NAME("academyName"),
    STATUS("status"),
    ADDRESS("roadAddress");

    private final String explain;

}
