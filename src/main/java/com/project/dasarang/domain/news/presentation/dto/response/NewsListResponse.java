package com.project.dasarang.domain.news.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class NewsListResponse {

    private int currentPage;
    private boolean hasMorePage;
    private List<NewsResponse> list;

}
