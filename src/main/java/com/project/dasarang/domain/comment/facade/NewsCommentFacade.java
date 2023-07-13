package com.project.dasarang.domain.comment.facade;

import com.project.dasarang.domain.comment.domain.NewsComment;
import com.project.dasarang.domain.comment.domain.repository.NewsCommentRepository;
import com.project.dasarang.domain.news.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NewsCommentFacade {

    private final NewsCommentRepository newsCommentRepository;

    @Transactional
    public void save(NewsComment newsComment) {
        newsCommentRepository.save(newsComment);
    }

}
