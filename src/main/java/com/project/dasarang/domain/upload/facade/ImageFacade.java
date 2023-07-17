package com.project.dasarang.domain.upload.facade;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.enums.ImageType;
import com.project.dasarang.domain.upload.domain.repository.ImageRepository;
import com.project.dasarang.domain.upload.exception.ImageMainExistsException;
import com.project.dasarang.domain.upload.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageFacade {

    private final ImageRepository imageRepository;

    public Image findByImageId(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> ImageNotFoundException.EXCEPTION);
    }

    public List<Image> findAllByPost(Post post) {
        return imageRepository.findAllByPost(post);
    }

    public List<Image> findallByNews(News news) {
        return imageRepository.findAllByNews(news);
    }

    public void existsByPostAndType(Post post, ImageType type) {
        imageRepository.findByPostAndType(post, type)
                .ifPresent(m -> {
                    throw ImageMainExistsException.EXCEPTION;
                });
    }

}
