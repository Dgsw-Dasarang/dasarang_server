package com.project.dasarang.domain.comment.service;

import com.project.dasarang.domain.comment.domain.PostComment;
import com.project.dasarang.domain.comment.exception.CommentEmptyException;
import com.project.dasarang.domain.comment.facade.PostCommentFacade;
import com.project.dasarang.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostCommentService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final PostCommentFacade postCommentFacade;

    @Transactional
    public void execute(CreateCommentRequest request, Long postId) {
        User user = userFacade.getCurrentUser();

        if (request.getComment().isEmpty())
            throw CommentEmptyException.EXCEPTION;

        Post post = postFacade.findByPostId(postId);

        PostComment postComment = request.toPostComment();
        postComment.setUser(user);
        postComment.setPost(post);

        postCommentFacade.save(postComment);
    }

}
