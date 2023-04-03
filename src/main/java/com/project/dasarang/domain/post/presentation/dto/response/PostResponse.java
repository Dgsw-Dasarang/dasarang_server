package com.project.dasarang.domain.post.presentation.dto.response;

import com.project.dasarang.domain.post.domain.enums.Category;
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
    private Category category;
    private List<ImageResponse> images;

}
