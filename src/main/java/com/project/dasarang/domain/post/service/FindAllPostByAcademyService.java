package com.project.dasarang.domain.post.service;

import com.project.dasarang.domain.education.facade.EducationFacade;
import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.post.presentation.dto.response.PostListResponse;
import com.project.dasarang.domain.post.presentation.dto.response.PostResponse;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.facade.ImageFacade;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllPostByAcademyService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final ImageFacade imageFacade;
    private final EducationFacade educationFacade;

    @Transactional
    public PostListResponse execute(int page, int size, String acaAsnum) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "modifiedDateTime");
        User author = userFacade.findUserByAcaAsnum(acaAsnum);

        Page<Post> posts = postFacade.findAllByAuthor(pageable, author);

        List<PostResponse> list = posts.stream()
                .map(post -> {
                    List<Image> images = imageFacade.findAllByPost(post);
                    return PostResponse.of(post, images,
                            educationFacade.findEducationByAcaAsnum(post.getAuthor().getOwnerNumber()).getAcademyName());
                })
                .collect(Collectors.toList());

        return PostListResponse.builder()
                .currentPage(posts.getNumber() + 1)
                .hasMorePage(posts.getTotalPages() > posts.getNumber() + 1)
                .list(list)
                .build();
    }

}
