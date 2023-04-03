package com.project.dasarang.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class PostListResponse {

    private int currentPage;
    private boolean hasMorePage;
    private List<PostResponse> list;

}
