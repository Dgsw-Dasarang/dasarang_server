package com.project.dasarang.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class PostResponse {

    private Long postId;
    private String title;
    private String content;
    private String academyName;
    private List<ImageResponse> images;

}
