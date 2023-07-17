package com.project.dasarang.domain.comment.service;

import com.project.dasarang.domain.comment.domain.NewsComment;
import com.project.dasarang.domain.comment.exception.CommentEmptyException;
import com.project.dasarang.domain.comment.facade.NewsCommentFacade;
import com.project.dasarang.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.facade.NewsFacade;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateNewsCommentService {

    private final UserFacade userFacade;
    private final NewsFacade newsFacade;
    private final NewsCommentFacade newsCommentFacade;

    @Transactional
    public void execute(CreateCommentRequest request, Long newsId) {
        User user = userFacade.getCurrentUser();

        if (request.getComment().isEmpty())
            throw CommentEmptyException.EXCEPTION;

        News news = newsFacade.findByNewsId(newsId);

        NewsComment newsComment = request.toNewsComment();
        newsComment.setUser(user);
        newsComment.setNews(news);

        newsCommentFacade.save(newsComment);
    }

}
