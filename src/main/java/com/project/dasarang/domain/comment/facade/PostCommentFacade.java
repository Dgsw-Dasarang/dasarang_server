package com.project.dasarang.domain.comment.facade;

import com.project.dasarang.domain.comment.domain.PostComment;
import com.project.dasarang.domain.comment.domain.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostCommentFacade {

    private final PostCommentRepository postCommentRepository;

    @Transactional
    public void save(PostComment postComment) {
        postCommentRepository.save(postComment);
    }

}
