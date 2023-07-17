package com.project.dasarang.domain.post.service;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.post.facade.PostFacade;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.exception.OwnerForbiddenException;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;

    @Transactional
    public void execute(Long postId) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.findByPostId(postId);

        if(post.getAuthor().equals(user))
            throw OwnerForbiddenException.EXCEPTION;

        postFacade.delete(post);
    }

}
