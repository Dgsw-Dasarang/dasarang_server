package com.project.dasarang.domain.news.presentation.dto.request;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.domain.enums.NewsCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateNewsRequest {

    private String title;
    private String content;
    private NewsCategory category;
    private List<Long> images;

    public News toEntity() {
        return News.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .build();
    }

}
