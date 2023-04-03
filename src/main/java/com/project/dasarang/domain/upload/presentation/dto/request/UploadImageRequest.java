package com.project.dasarang.domain.upload.presentation.dto.request;

import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.enums.ImageType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadImageRequest {

    private ImageType type;

    public Image toEntity(String url) {
        return Image.builder()
                .url(url)
                .type(type)
                .build();
    }

}
