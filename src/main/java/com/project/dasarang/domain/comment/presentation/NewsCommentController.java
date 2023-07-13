package com.project.dasarang.domain.comment.presentation;

import com.project.dasarang.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.project.dasarang.domain.comment.service.CreateNewsCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment/news")
@RequiredArgsConstructor
@Tag(name = "소식 댓글 서버 (학교, 유치원, 어린이집, 교육청)")
public class NewsCommentController {

    private final CreateNewsCommentService createNewsCommentService;

    @Operation(summary = "댓글 작성하기")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewsComment(
            @RequestBody CreateCommentRequest request
    ) {
        createNewsCommentService.execute(request);
    }

}
