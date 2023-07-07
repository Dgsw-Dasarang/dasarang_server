package com.project.dasarang.domain.news.domain.repository;

import com.project.dasarang.domain.news.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.title LIKE CONCAT('%', :content, '%') OR n.content LIKE CONCAT('%', :content, '%')")
    Page<News> findAllByTitleOrContent(Pageable pageable, String content);

    @Query(value = "SELECT * FROM tb_news WHERE category LIKE CONCAT('%', :content, '%')", nativeQuery = true)
    Page<News> findAllByCategory(Pageable pageable, String content);

}
