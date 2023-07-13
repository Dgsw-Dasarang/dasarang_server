package com.project.dasarang.domain.comment.domain.repository;

import com.project.dasarang.domain.comment.domain.NewsComment;
import com.project.dasarang.domain.news.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {

    Page<NewsComment> findAllByNews(News news, Pageable pageable);

}
