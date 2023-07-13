package com.project.dasarang.domain.comment.service;

import com.project.dasarang.domain.comment.domain.NewsComment;
import com.project.dasarang.domain.comment.domain.PostComment;
import com.project.dasarang.domain.comment.facade.NewsCommentFacade;
import com.project.dasarang.domain.comment.presentation.dto.response.CommentListResponse;
import com.project.dasarang.domain.comment.presentation.dto.response.CommentResponse;
import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.facade.NewsFacade;
import com.project.dasarang.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindNewsCommentByNewsService {

    private final NewsFacade newsFacade;
    private final NewsCommentFacade newsCommentFacade;

    @Transactional
    public CommentListResponse execute(Long newsId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDateTime");

        News news = newsFacade.findByNewsId(newsId);
        Page<NewsComment> list = newsCommentFacade.findAllByNews(news, pageable);

        return CommentListResponse.builder()
                .list(list.map(CommentResponse::of)
                        .stream().collect(Collectors.toList()))
                .build();
    }

}
