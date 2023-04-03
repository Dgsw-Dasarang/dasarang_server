package com.project.dasarang.domain.post.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.domain.repository.PostRepository;
import com.project.dasarang.domain.post.exception.PostCreateWrongException;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.post.presentation.dto.request.CreatePostRequest;
import com.project.dasarang.domain.post.presentation.dto.response.PostListResponse;
import com.project.dasarang.domain.post.presentation.dto.response.PostResponse;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.repository.ImageRepository;
import com.project.dasarang.domain.upload.exception.ImageNotFoundException;
import com.project.dasarang.domain.upload.exception.ImageWrongException;
import com.project.dasarang.domain.upload.facade.UploadFacade;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostFacade postFacade;
    private final UserFacade userFacade;
    private final UploadFacade uploadFacade;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void createPost(CreatePostRequest request) {
        User user = userFacade.getCurrentUser();

        if(!user.getAuthority().equals(UserType.ROLE_OWNER))
            throw PostCreateWrongException.EXCEPTION;

        Post post = request.toEntity();

        if(!request.getImages().isEmpty()) {
            List<Image> imageList = request.getImages().stream().map(item -> {
                Image image = imageRepository.findById(item)
                        .orElseThrow(() -> ImageNotFoundException.EXCEPTION);

                if(!image.getAuthor().getId().equals(user.getId()))
                    throw ImageWrongException.EXCEPTION;

                return image;
            }).peek(image -> image.setPost(post)).collect(Collectors.toList());

            post.addImage(imageList);
            post.setAuthor(user);
        }

        postRepository.save(post);
    }

    @Override
    public PostListResponse getAllPost(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");

        Page<Post> posts = postFacade.findAll(pageable);
        List<PostResponse> list = posts.stream()
                .map(post -> {
                    List<Image> images = uploadFacade.findAllByPost(post);
                    return ResponseUtil.getPostResponse(post, images);
                })
                .collect(Collectors.toList());

        return PostListResponse.builder()
                .currentPage(posts.getNumber() + 1)
                .hasMorePage(posts.getTotalPages() > posts.getNumber())
                .list(list)
                .build();
    }
}
