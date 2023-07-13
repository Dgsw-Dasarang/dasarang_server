package com.project.dasarang.domain.comment.domain.repository;

import com.project.dasarang.domain.comment.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
