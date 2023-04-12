package com.project.dasarang.domain.news.facade;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.domain.enums.SearchCategory;
import com.project.dasarang.domain.news.domain.repository.NewsRepository;
import com.project.dasarang.domain.news.exception.NewsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsFacade {

    private final NewsRepository newsRepository;

    public News findByNewsId(Long newsId) {
        return newsRepository.findById(newsId)
                .orElseThrow(() -> NewsNotFoundException.EXCEPTION);
    }

    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public Page<News> findAllByTitleOrContent(Pageable pageable, String content) {
        return newsRepository.findAllByTitleOrContent(pageable, content);
    }

    public Page<News> findAllByCategory(Pageable pageable, String content) {
        return newsRepository.findAllByCategory(pageable, content);
    }

}
