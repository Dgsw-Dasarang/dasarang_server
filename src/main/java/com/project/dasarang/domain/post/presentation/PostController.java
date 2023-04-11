package com.project.dasarang.domain.post.presentation;

import com.project.dasarang.domain.post.presentation.dto.request.CreatePostRequest;
import com.project.dasarang.domain.post.presentation.dto.response.PostListResponse;
import com.project.dasarang.domain.post.presentation.dto.response.PostResponse;
import com.project.dasarang.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "글 서버")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
            @RequestBody CreatePostRequest request
    ) {
        postService.createPost(request);
    }

    @Operation(summary = "게시글 상세 조회하기")
    @GetMapping("/{id}")
    public PostResponse getPostById(
            @PathVariable("id") Long postId
    ) {
        return postService.getPostById(postId);
    }

    @Operation(summary = "전체 게시글 조회하기 (page랑 size 필요)")
    @GetMapping("/list")
    public PostListResponse getPost(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return postService.getAllPost(page, size);
    }

    @Operation(summary = "학원별 게시글 조회하기 (page랑 size랑 학원지정번호 필요)")
    @GetMapping("/list/{aca-number}")
    public PostListResponse getPostByAcaAsnum(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @PathVariable("aca-number") String number
    ) {
        return postService.getAllPostByAcademy(page, size, number);
    }

}
