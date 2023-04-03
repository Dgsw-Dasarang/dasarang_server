package com.project.dasarang.domain.post.service;

import com.project.dasarang.domain.post.presentation.dto.request.CreatePostRequest;
import com.project.dasarang.domain.post.presentation.dto.response.PostListResponse;

public interface PostService {

    void createPost(CreatePostRequest request);

    PostListResponse getAllPost(int page, int size);

}
