package com.project.dasarang.domain.news.service;

import com.project.dasarang.domain.news.domain.enums.SearchCategory;
import com.project.dasarang.domain.news.presentation.dto.request.CreateNewsRequest;
import com.project.dasarang.domain.news.presentation.dto.response.NewsListResponse;
import com.project.dasarang.domain.news.presentation.dto.response.NewsResponse;

public interface NewsService {

    void createNews(CreateNewsRequest request);

    NewsListResponse getNewsAllByCategory(int page, int size, SearchCategory category, String content);

    NewsListResponse getNewsAll(int page, int size);

    NewsResponse getNewsById(Long id);

}
