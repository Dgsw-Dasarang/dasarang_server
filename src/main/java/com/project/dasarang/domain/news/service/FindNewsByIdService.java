package com.project.dasarang.domain.news.service;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.facade.NewsFacade;
import com.project.dasarang.domain.news.presentation.dto.response.NewsResponse;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindNewsByIdService {

    private final NewsFacade newsFacade;
    private final ImageFacade imageFacade;

    @Transactional
    public NewsResponse execute(Long newsId) {
        News news = newsFacade.findByNewsId(newsId);
        List<Image> images = imageFacade.findallByNews(news);

        return NewsResponse.of(news, images);
    }

}
