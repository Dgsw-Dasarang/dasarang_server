package com.project.dasarang.global.infra.education.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EducationCategory {

    NUMBER("aca_asnum"),
    ZONE_NAME("admst_zone_name"),
    NAME("academy_name");

    private final String explain;

}
