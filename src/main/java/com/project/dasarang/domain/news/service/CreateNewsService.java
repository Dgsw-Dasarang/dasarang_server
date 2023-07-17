package com.project.dasarang.domain.news.service;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.facade.NewsFacade;
import com.project.dasarang.domain.news.presentation.dto.request.CreateNewsRequest;
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
public class CreateNewsService {

    private final UserFacade userFacade;
    private final NewsFacade newsFacade;
    private final ImageRepository imageRepository;

    @Transactional
    public void execute(CreateNewsRequest request) {
        User user = userFacade.getCurrentUser();

        News news = request.toEntity();

        if(!request.getImages().isEmpty()) {
            List<Image> imageList = request.getImages().stream().map(item -> {
                Image image = imageRepository.findById(item)
                        .orElseThrow(() -> ImageNotFoundException.EXCEPTION);

                if(!Objects.isNull(image.getPost()))
                    throw ImageUsedException.EXCEPTION;

                if(!image.getAuthor().getId().equals(user.getId()))
                    throw ImageWrongException.EXCEPTION;

                return image;
            }).peek(image -> image.setNews(news)).collect(Collectors.toList());

            news.addImage(imageList);
        }

        newsFacade.save(news);
    }

}
