package com.project.dasarang.domain.news.service;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.facade.NewsFacade;
import com.project.dasarang.domain.news.presentation.dto.request.UpdateNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateNewsService {

    private final NewsFacade newsFacade;

    @Transactional
    public void execute(Long id, UpdateNewsRequest request) {
        News news = newsFacade.findByNewsId(id);
        news.modifyNews(request);
    }

}
