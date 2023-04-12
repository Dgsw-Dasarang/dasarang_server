package com.project.dasarang.domain.news.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchCategory {

    TITLE("title"),
    CONTENT("content"),
    CATEGORY("category");

    private final String explain;

}
