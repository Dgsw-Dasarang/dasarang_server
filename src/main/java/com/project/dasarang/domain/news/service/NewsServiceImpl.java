package com.project.dasarang.domain.news.service;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.domain.enums.SearchCategory;
import com.project.dasarang.domain.news.domain.repository.NewsRepository;
import com.project.dasarang.domain.news.facade.NewsFacade;
import com.project.dasarang.domain.news.presentation.dto.request.CreateNewsRequest;
import com.project.dasarang.domain.news.presentation.dto.request.UpdateNewsRequest;
import com.project.dasarang.domain.news.presentation.dto.response.NewsListResponse;
import com.project.dasarang.domain.news.presentation.dto.response.NewsResponse;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.repository.ImageRepository;
import com.project.dasarang.domain.upload.exception.ImageNotFoundException;
import com.project.dasarang.domain.upload.exception.ImageUsedException;
import com.project.dasarang.domain.upload.exception.ImageWrongException;
import com.project.dasarang.domain.upload.facade.UploadFacade;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsFacade newsFacade;
    private final UserFacade userFacade;
    private final UploadFacade uploadFacade;
    private final NewsRepository newsRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void createNews(CreateNewsRequest request) {
        User user = userFacade.getCurrentUser();
        userFacade.checkAdminPermissions();

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

        newsRepository.save(news);
    }

    @Override
    @Transactional
    public void updateNews(Long id, UpdateNewsRequest request) {
        userFacade.checkAdminPermissions();

        News news = newsFacade.findByNewsId(id);
        news.modifyNews(request);

        newsRepository.save(news);
    }

    @Override
    @Transactional
    public void deleteNews(Long id) {
        userFacade.checkAdminPermissions();

        News news = newsFacade.findByNewsId(id);

        newsRepository.delete(news);
    }

    @Override
    public NewsListResponse getNewsAllByCategory(int page, int size, SearchCategory category, String content) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedDateTime");

        Page<News> newsList = null;
        if(category.equals(SearchCategory.CONTENT))
             newsList = newsFacade.findAllByTitleOrContent(pageable, content);
        else if(category.equals(SearchCategory.CATEGORY))
            newsList = newsFacade.findAllByCategory(pageable, content);

        List<NewsResponse> list = newsList.stream()
                .map(news -> {
                    List<Image> images = uploadFacade.findallByNews(news);
                    return ResponseUtil.getNewsResponse(news, images);
                })
                .collect(Collectors.toList());

        return NewsListResponse.builder()
                .currentPage(newsList.getNumber() + 1)
                .hasMorePage(newsList.getTotalPages() > newsList.getNumber() + 1)
                .list(list)
                .build();
    }

    @Override
    public NewsListResponse getNewsAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedDateTime");

        Page<News> newsList = newsFacade.findAll(pageable);

        List<NewsResponse> list = newsList.stream()
                .map(news -> {
                    List<Image> images = uploadFacade.findallByNews(news);
                    return ResponseUtil.getNewsResponse(news, images);
                })
                .collect(Collectors.toList());

        return NewsListResponse.builder()
                .currentPage(newsList.getNumber() + 1)
                .hasMorePage(newsList.getTotalPages() > newsList.getNumber() + 1)
                .list(list)
                .build();
    }

    @Override
    public NewsResponse getNewsById(Long newsId) {
        News news = newsFacade.findByNewsId(newsId);
        List<Image> images = uploadFacade.findallByNews(news);

        return ResponseUtil.getNewsResponse(news, images);
    }

}
