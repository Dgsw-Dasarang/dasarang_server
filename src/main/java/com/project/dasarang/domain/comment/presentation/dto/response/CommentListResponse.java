package com.project.dasarang.domain.comment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class CommentListResponse {

    private List<CommentResponse> list;

}
