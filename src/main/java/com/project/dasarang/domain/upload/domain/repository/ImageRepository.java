package com.project.dasarang.domain.upload.domain.repository;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.upload.domain.enums.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByPost(Post post);

    Optional<Image> findByPostAndType(Post post, ImageType type);

    List<Image> findAllByNews(News news);

}
