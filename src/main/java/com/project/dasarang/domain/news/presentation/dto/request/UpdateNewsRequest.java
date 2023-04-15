package com.project.dasarang.domain.news.presentation.dto.request;

import com.project.dasarang.domain.news.domain.enums.NewsCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateNewsRequest {

    private String title;
    private String content;
    private NewsCategory category;

}
