package com.project.dasarang.domain.comment.service;

import com.project.dasarang.domain.comment.domain.PostComment;
import com.project.dasarang.domain.comment.facade.PostCommentFacade;
import com.project.dasarang.domain.comment.presentation.dto.response.CommentListResponse;
import com.project.dasarang.domain.comment.presentation.dto.response.CommentResponse;
import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
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
public class FindPostCommentByPostService {

    private final PostFacade postFacade;
    private final PostCommentFacade postCommentFacade;

    @Transactional
    public CommentListResponse execute(Long postId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDateTime");

        Post post = postFacade.findByPostId(postId);
        Page<PostComment> list = postCommentFacade.findAllByPost(post, pageable);

        return CommentListResponse.builder()
                .list(list.map(CommentResponse::of)
                        .stream().collect(Collectors.toList()))
                .build();
    }

}
