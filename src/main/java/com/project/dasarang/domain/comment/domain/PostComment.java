package com.project.dasarang.domain.comment.domain;

import com.project.dasarang.domain.post.domain.Post;
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
@Table(name = "tb_post_comment")
public class PostComment extends BaseTime {

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
    @JoinColumn(name = "fk_post")
    private Post post;
    public void setPost(Post post) {
        this.post = post;
    }

    private String comment;

    @Builder
    public PostComment(String comment) {
        this.comment = comment;
    }
}
