package com.project.dasarang.domain.post.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.post.presentation.dto.request.CreatePostRequest;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.repository.ImageRepository;
import com.project.dasarang.domain.upload.exception.ImageNotFoundException;
import com.project.dasarang.domain.upload.exception.ImageUsedException;
import com.project.dasarang.domain.upload.exception.ImageWrongException;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final ImageRepository imageRepository;

    @Transactional
    public void execute(CreatePostRequest request) {
        User user = userFacade.getCurrentUser();

        Post post = request.toEntity();

        if (!request.getImages().isEmpty()) {
            List<Image> imageList = request.getImages().stream().map(item -> {
                Image image = imageRepository.findById(item)
                        .orElseThrow(() -> ImageNotFoundException.EXCEPTION);

                if (!Objects.isNull(image.getPost()))
                    throw ImageUsedException.EXCEPTION;

                if (!image.getAuthor().getId().equals(user.getId()))
                    throw ImageWrongException.EXCEPTION;

                return image;
            }).peek(image -> image.setPost(post)).collect(Collectors.toList());

            post.addImage(imageList);
            post.setAuthor(user);
        }

        postFacade.save(post);
    }

}
