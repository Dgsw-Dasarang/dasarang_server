package com.project.dasarang.domain.news.domain.repository;

import com.project.dasarang.domain.news.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE :category LIKE CONCAT('%', :content, '%')")
    Page<News> findbyCategory(Pageable pageable, String category, String content);

}
