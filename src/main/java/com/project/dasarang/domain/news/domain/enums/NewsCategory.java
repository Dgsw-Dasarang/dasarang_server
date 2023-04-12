package com.project.dasarang.domain.news.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NewsCategory {

    SCHOOL("학교"),
    PRESCHOOL("유치원"),
    DAYCARE_CENTER("어린이집"),
    EDUCATION_OFFICE("교육청");

    private final String value;


}
