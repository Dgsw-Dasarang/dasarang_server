package com.project.dasarang.domain.comment.domain;

import com.project.dasarang.domain.news.domain.News;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_news_comment")
public class NewsComment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "fk_news")
    private News news;
    public void setNews(News news) {
        this.news = news;
    }

    private String comment;

    @Builder
    public NewsComment(String comment) {
        this.comment = comment;
    }
}
