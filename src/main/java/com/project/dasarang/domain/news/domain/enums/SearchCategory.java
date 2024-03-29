package com.project.dasarang.domain.news.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchCategory {

    CONTENT("content"),
    CATEGORY("category"),
    NEW("new");

    private final String explain;

}
