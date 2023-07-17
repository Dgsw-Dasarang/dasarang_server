package com.project.dasarang.domain.news.service;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.facade.NewsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteNewsService {

    private final NewsFacade newsFacade;

    @Transactional
    public void execute(Long id) {
        News news = newsFacade.findByNewsId(id);
        newsFacade.delete(news);
    }

}
