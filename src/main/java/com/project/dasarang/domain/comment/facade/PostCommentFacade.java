package com.project.dasarang.domain.comment.facade;

import com.project.dasarang.domain.comment.domain.PostComment;
import com.project.dasarang.domain.comment.domain.repository.PostCommentRepository;
import com.project.dasarang.domain.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<PostComment> findAllByPost(Post post, Pageable pageable) {
        return postCommentRepository.findAllByPost(post, pageable);
    }

}
