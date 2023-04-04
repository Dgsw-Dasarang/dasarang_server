package com.project.dasarang.domain.upload.presentation;

import com.project.dasarang.domain.upload.domain.enums.ImageType;
import com.project.dasarang.domain.upload.presentation.dto.request.UploadImageRequest;
import com.project.dasarang.domain.upload.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
@Tag(name = "이미지 서버")
public class UploadController {

    private final UploadService uploadService;

    @Operation(summary = "이미지 업로드")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Long> uploadImage(
            @RequestParam("type") ImageType type,
            @RequestParam("image") List<MultipartFile> files
    ) {
        return uploadService.uploadImage(files, type);
    }
    @Operation(summary = "게시글의 모든 이미지 가져오기")
    @GetMapping("/{post-id}")
    public List<String> getImageByPost(
            @PathVariable("post-id") Long postId
    ) {
        return uploadService.getImageUrlByPost(postId);
    }

}
