package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.post.presentation.dto.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUpdatePostService {

    private final PostFacade postFacade;

    @Transactional
    public void execute(Long postId, UpdatePostRequest request) {
        Post post = postFacade.findByPostId(postId);
        post.modifyPost(request);
    }

}
