package com.project.dasarang.domain.news.presentation.dto.response;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.domain.enums.NewsCategory;
import com.project.dasarang.domain.post.presentation.dto.response.ImageResponse;
import com.project.dasarang.domain.upload.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class NewsResponse {

    private Long newsId;
    private String title;
    private String content;
    private NewsCategory category;
    private List<ImageResponse> images;

    public static NewsResponse of(News news, List<Image> images) {
        return NewsResponse.builder()
                .newsId(news.getNewsId())
                .title(news.getTitle())
                .content(news.getContent())
                .category(news.getCategory())
                .images(images != null ? images.stream().map(
                        ImageResponse::of
                ).collect(Collectors.toList()) : null)
                .build();
    }

}
