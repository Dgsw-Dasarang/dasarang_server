package com.project.dasarang.domain.post.service;

import com.project.dasarang.domain.education.facade.EducationFacade;
import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.post.presentation.dto.response.PostResponse;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPostByIdService {

    private final PostFacade postFacade;
    private final ImageFacade imageFacade;
    private final EducationFacade educationFacade;

    @Transactional
    public PostResponse execute(Long postId) {
        Post post = postFacade.findByPostId(postId);
        List<Image> images = imageFacade.findAllByPost(post);

        return PostResponse.of(post, images,
                educationFacade.findEducationByAcaAsnum(post.getAuthor().getOwnerNumber()).getAcademyName());
    }

}
