package com.project.dasarang.domain.news.domain.repository;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.news.domain.enums.NewsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.title LIKE CONCAT('%', :content, '%') OR n.content LIKE CONCAT('%', :content, '%')")
    Page<News> findAllByTitleOrContent(Pageable pageable, String content);

    Page<News> findAllByCategory(Pageable pageable, NewsCategory content);

}
