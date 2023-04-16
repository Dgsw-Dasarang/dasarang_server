package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.domain.repository.PostRepository;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.post.presentation.dto.request.UpdatePostRequest;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostAdminServiceImpl implements PostAdminService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request) {
        Post post = postFacade.findByPostId(postId);
        post.modifyPost(request);

        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postFacade.findByPostId(postId);

        postRepository.delete(post);
    }
}
