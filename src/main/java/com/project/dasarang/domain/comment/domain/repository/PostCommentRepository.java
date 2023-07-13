package com.project.dasarang.domain.comment.domain.repository;

import com.project.dasarang.domain.comment.domain.PostComment;
import com.project.dasarang.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    Page<PostComment> findAllByPost(Post post, Pageable pageable);

}
