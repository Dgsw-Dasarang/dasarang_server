package com.project.dasarang.domain.upload.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageType {

    MAIN("메인"),
    SUB("서브");

    private final String type;

}
