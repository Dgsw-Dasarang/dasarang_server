package com.project.dasarang.domain.post.presentation.dto.request;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.domain.enums.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePostRequest {

    private String title;
    private String content;
    private Category category;
    private List<Long> images;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .build();
    }

}
