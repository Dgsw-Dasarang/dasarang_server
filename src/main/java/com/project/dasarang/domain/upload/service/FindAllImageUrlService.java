package com.project.dasarang.domain.upload.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.facade.ImageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllImageUrlService {

    private final PostFacade postFacade;
    private final ImageFacade imageFacade;

    @Transactional
    public List<String> execute(Long postId) {
        Post post = postFacade.findByPostId(postId);

        return imageFacade.findAllByPost(post).stream()
                .map(Image::getUrl).collect(Collectors.toList());
    }

}
