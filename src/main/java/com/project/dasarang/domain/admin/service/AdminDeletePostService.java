package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDeletePostService {

    private final PostFacade postFacade;


    @Transactional
    public void execute(Long postId) {
        Post post = postFacade.findByPostId(postId);

        postFacade.delete(post);
    }

}
