package com.project.dasarang.domain.upload.service;

import com.project.dasarang.domain.upload.domain.enums.ImageType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

    Long uploadImage(MultipartFile file, ImageType type);

    List<String> getImageUrlByPost(Long post_id);

}
