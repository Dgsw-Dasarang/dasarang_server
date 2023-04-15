package com.project.dasarang.domain.post.service;

import com.project.dasarang.domain.post.presentation.dto.request.CreatePostRequest;
import com.project.dasarang.domain.post.presentation.dto.request.UpdatePostRequest;
import com.project.dasarang.domain.post.presentation.dto.response.PostListResponse;
import com.project.dasarang.domain.post.presentation.dto.response.PostResponse;

public interface PostService {

    void createPost(CreatePostRequest request);

    void updatePost(Long postId, UpdatePostRequest request);

    void deletePost(Long postId);

    PostResponse getPostById(Long postId);

    PostListResponse getAllPost(int page, int size);

    PostListResponse getAllPostByAcademy(int page, int size, String acaAsnum);

}
