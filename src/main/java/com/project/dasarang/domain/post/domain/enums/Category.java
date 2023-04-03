package com.project.dasarang.domain.post.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    CATEGORY1("카테고리1"),
    CATEGORY2("카테고리2");

    private final String category;

}
