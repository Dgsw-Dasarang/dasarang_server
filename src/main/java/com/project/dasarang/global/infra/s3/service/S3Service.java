package com.project.dasarang.global.infra.s3.service;

import com.amazonaws.services.s3.model.*;
import com.project.dasarang.domain.upload.domain.enums.ImageType;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.infra.s3.AWSProperties;
import com.project.dasarang.global.infra.s3.S3Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AWSProperties awsProperties;
    private final S3Configuration aws;

    public String s3UploadFile(User user, MultipartFile file, ImageType type) {
        String originName = type.equals(ImageType.MAIN) ? "main_image" : "sub_image";
        String fileName = createFileName(originName);
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            aws.amazonS3Client().putObject(
                    new PutObjectRequest(awsProperties.getBucket(), fileName, file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return awsProperties.getUrl() + fileName;
    }

    private String createFileName(String originalName) {
        return "image/" + UUID.randomUUID() + "-" + originalName;
    }

    public String getImageUrl(String fileName) {
        try (S3Object s3Object = aws.amazonS3Client().getObject(awsProperties.getBucket(), "image/" + fileName)) {
            return awsProperties.getUrl() + s3Object.getKey();
        } catch (AmazonS3Exception | IOException e) {
            return null;
        }
    }

}
