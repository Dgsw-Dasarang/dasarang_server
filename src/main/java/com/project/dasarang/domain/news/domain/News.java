package com.project.dasarang.domain.news.domain;

import com.project.dasarang.domain.comment.domain.NewsComment;
import com.project.dasarang.domain.news.domain.enums.NewsCategory;
import com.project.dasarang.domain.news.presentation.dto.request.UpdateNewsRequest;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_news")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private NewsCategory category;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> imageList;
    public void addImage(List<Image> image) {
        image.stream().map(it ->
                getImageList().add(it)
        ).close();
    }

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsComment> commentList;
    public void addComment(NewsComment comment) {
        comment.setNews(this);
        this.commentList.add(comment);
    }

    public void modifyNews(UpdateNewsRequest data) {
        this.title = data.getTitle();
        this.content = data.getContent();
        this.category = data.getCategory();
    }

    @Builder
    public News(String title, String content, NewsCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.imageList = new ArrayList<>();
        this.commentList = new ArrayList<>();
    }
}
