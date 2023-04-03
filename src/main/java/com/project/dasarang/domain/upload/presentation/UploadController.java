package com.project.dasarang.domain.upload.presentation;

import com.project.dasarang.domain.upload.domain.enums.ImageType;
import com.project.dasarang.domain.upload.presentation.dto.request.UploadImageRequest;
import com.project.dasarang.domain.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Long uploadImage(
            @RequestParam("type") ImageType type,
            @RequestParam("image") MultipartFile file
    ) {
        return uploadService.uploadImage(file, type);
    }

    @GetMapping("/{post-id}")
    public List<String> getImageByPost(
            @PathVariable("post-id") Long postId
    ) {
        return uploadService.getImageUrlByPost(postId);
    }

}
