package com.project.dasarang.domain.post.presentation.dto.response;

import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.enums.ImageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class ImageResponse {

    private String url;
    private ImageType type;

    public static ImageResponse of(Image image) {
        return ImageResponse.builder()
                .url(image.getUrl())
                .type(image.getType())
                .build();
    }

}
