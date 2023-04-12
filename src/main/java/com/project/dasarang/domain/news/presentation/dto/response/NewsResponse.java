package com.project.dasarang.domain.news.presentation.dto.response;

import com.project.dasarang.domain.news.domain.enums.NewsCategory;
import com.project.dasarang.domain.post.presentation.dto.response.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class NewsResponse {

    private Long newsId;
    private String title;
    private String content;
    private NewsCategory category;
    private List<ImageResponse> images;

}
