package com.project.dasarang.domain.comment.presentation.dto.response;

import com.project.dasarang.domain.comment.domain.NewsComment;
import com.project.dasarang.domain.comment.domain.PostComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter @Builder
@AllArgsConstructor
public class CommentResponse {

    private String comment;
    private String author;
    private String createdAt;

    public static CommentResponse of(PostComment postComment) {
        return CommentResponse.builder()
                .comment(postComment.getComment())
                .author(postComment.getUser().getUserId())
                .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        .format(postComment.getCreatedDateTime()))
                .build();
    }

    public static CommentResponse of(NewsComment newsComment) {
        return CommentResponse.builder()
                .comment(newsComment.getComment())
                .author(newsComment.getUser().getUserId())
                .createdAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        .format(newsComment.getCreatedDateTime()))
                .build();
    }

}
