package com.project.dasarang.domain.news.facade;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.domain.enums.NewsCategory;
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
public class NewsFacade {

    private final NewsRepository newsRepository;

    @Transactional(readOnly = true)
    public News findByNewsId(Long newsId) {
        return newsRepository.findById(newsId)
                .orElseThrow(() -> NewsNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<News> findAllByTitleOrContent(Pageable pageable, String content) {
        return newsRepository.findAllByTitleOrContent(pageable, content);
    }

    @Transactional(readOnly = true)
    public Page<News> findAllByCategory(SearchCategory category, Pageable pageable, String content) {
        if(category.equals(SearchCategory.CONTENT))
            return newsRepository.findAllByTitleOrContent(pageable, content);
        else if(category.equals(SearchCategory.CATEGORY))
            return newsRepository.findAllByCategory(pageable, NewsCategory.valueOf(content));
        else if(category.equals(SearchCategory.NEW))
            return newsRepository.findAll(pageable);

        return null;
    }

    @Transactional(readOnly = true)
    public Page<News> findAllByCategory(Pageable pageable, String content) {
        return newsRepository.findAllByCategory(pageable, NewsCategory.valueOf(content));
    }

    @Transactional
    public void save(News news) {
        newsRepository.save(news);
    }

    @Transactional
    public void delete(News news) {
        newsRepository.delete(news);
    }

}
