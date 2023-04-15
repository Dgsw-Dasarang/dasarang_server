package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.post.presentation.dto.request.UpdatePostRequest;

public interface PostAdminService {

    void updatePost(Long postId, UpdatePostRequest request);

    void deletePost(Long postId);

}
