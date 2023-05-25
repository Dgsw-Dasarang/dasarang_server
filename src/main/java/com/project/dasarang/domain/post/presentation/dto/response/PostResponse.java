package com.project.dasarang.domain.post.presentation.dto.response;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.upload.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Builder
@AllArgsConstructor
public class PostResponse {

    private Long postId;
    private String title;
    private String content;
    private String academyName;
    private List<ImageResponse> images;

    public static PostResponse of(Post post, List<Image> images, String academyName) {
        return PostResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .academyName(academyName)
                .images(images != null ? images.stream().map(
                        ImageResponse::of
                ).collect(Collectors.toList()) : null)
                .build();
    }

}
