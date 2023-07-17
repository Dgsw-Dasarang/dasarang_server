package com.project.dasarang.domain.news.service;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.domain.enums.SearchCategory;
import com.project.dasarang.domain.news.facade.NewsFacade;
import com.project.dasarang.domain.news.presentation.dto.response.NewsListResponse;
import com.project.dasarang.domain.news.presentation.dto.response.NewsResponse;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllNewsByCategoryService {

    private final NewsFacade newsFacade;
    private final ImageFacade imageFacade;

    @Transactional
    public NewsListResponse execute(int page, int size, SearchCategory category, String content) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedDateTime");

        Page<News> newsList = newsFacade.findAllByCategory(category, pageable, content);

        List<NewsResponse> list = newsList.stream()
                .map(news -> {
                    List<Image> images = imageFacade.findallByNews(news);
                    return NewsResponse.of(news, images);
                }).collect(Collectors.toList());

        return NewsListResponse.builder()
                .currentPage(newsList.getNumber() + 1)
                .hasMorePage(newsList.getTotalPages() > newsList.getNumber() + 1)
                .list(list)
                .build();
    }

}
