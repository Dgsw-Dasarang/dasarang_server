package com.project.dasarang.domain.admin.presentation;

import com.project.dasarang.domain.admin.service.AdminDeletePostService;
import com.project.dasarang.domain.admin.service.AdminUpdatePostService;
import com.project.dasarang.domain.post.presentation.dto.request.UpdatePostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/post")
@RequiredArgsConstructor
@Tag(name = "게시글 관리 서버")
public class PostAdminController {

    private AdminUpdatePostService adminUpdatePostService;
    private AdminDeletePostService adminDeletePostService;

    @Operation(summary = "게시글 강제 수정")
    @PatchMapping("/{id}")
    public void updatePost(
            @PathVariable("id") Long id,
            @RequestBody UpdatePostRequest request
    ) {
        adminUpdatePostService.execute(id, request);
    }

    @Operation(summary = "게시글 강제 삭제")
    @DeleteMapping("/{id}")
    public void deletePost(
            @PathVariable("id") Long id
    ) {
        adminDeletePostService.execute(id);
    }

}
