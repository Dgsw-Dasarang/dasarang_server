package com.project.dasarang.domain.comment.presentation.dto.request;

import com.project.dasarang.domain.comment.domain.NewsComment;
import com.project.dasarang.domain.comment.domain.PostComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCommentRequest {

    private String comment;

    public PostComment toPostComment() {
        return PostComment.builder()
                .comment(this.comment)
                .build();
    }

    public NewsComment toNewsComment() {
        return NewsComment.builder()
                .comment(this.comment)
                .build();
    }

}
