package com.project.dasarang.domain.post.facade;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.upload.domain.repository.ImageRepository;
import com.project.dasarang.domain.post.domain.repository.PostRepository;
import com.project.dasarang.domain.post.exception.PostNotFoundException;
import com.project.dasarang.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Post findByPostId(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByAuthor(Pageable pageable, User author) {
        return postRepository.findAllByAuthor(pageable, author);
    }

    @Transactional
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Transactional
    public void save(Post post) {
        postRepository.save(post);
    }

}
