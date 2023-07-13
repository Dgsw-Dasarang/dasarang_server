package com.project.dasarang.domain.comment.domain.repository;

import com.project.dasarang.domain.comment.domain.NewsComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {
}
