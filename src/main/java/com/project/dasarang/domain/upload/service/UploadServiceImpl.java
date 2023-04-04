package com.project.dasarang.domain.upload.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.enums.ImageType;
import com.project.dasarang.domain.upload.domain.repository.ImageRepository;
import com.project.dasarang.domain.upload.facade.UploadFacade;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.infra.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final S3Service s3Service;
    private final UserFacade userFacade;
    private final UploadFacade uploadFacade;
    private final PostFacade postFacade;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public List<Long> uploadImage(List<MultipartFile> files, ImageType type) {
        User user = userFacade.getCurrentUser();
        List<Image> images = files.stream()
                .map(file -> {
                    String url = s3Service.s3UploadFile(user, file, type);
                    return new Image(url, type);
                }).peek(image -> image.setAuthor(user))
                .collect(Collectors.toList());
        user.addImage(images);

        return imageRepository.saveAll(images).stream().map(Image::getImageId)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getImageUrlByPost(Long postId) {
        Post post = postFacade.findByPostId(postId);

        return uploadFacade.findAllByPost(post).stream()
                .map(Image::getUrl).collect(Collectors.toList());
    }
}
