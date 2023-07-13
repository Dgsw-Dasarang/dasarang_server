package com.project.dasarang.domain.comment.presentation;

import com.project.dasarang.domain.comment.presentation.dto.request.CreateCommentRequest;
import com.project.dasarang.domain.comment.presentation.dto.response.CommentListResponse;
import com.project.dasarang.domain.comment.service.CreatePostCommentService;
import com.project.dasarang.domain.comment.service.FindPostCommentByPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment/post")
@RequiredArgsConstructor
@Tag(name = "게시글 댓글 서버 (학원)")
public class PostCommentController {

    private final CreatePostCommentService createPostCommentService;
    private final FindPostCommentByPostService findPostCommentByPostService;

    @Operation(summary = "댓글 작성하기")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPostComment(
            @RequestBody CreateCommentRequest request
    ) {
        createPostCommentService.execute(request);
    }

    @Operation(summary = "게시글ID로 댓글 리스트 가져오기 (페이징 처리)")
    @GetMapping("/{id}")
    public CommentListResponse findPostCommentByPost(
            @PathVariable("id") Long postId,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return findPostCommentByPostService.execute(postId, page, size);
    }

}
