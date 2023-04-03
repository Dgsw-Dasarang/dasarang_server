package com.project.dasarang.domain.upload.domain;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.upload.domain.enums.ImageType;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "tb_image")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Image extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String url;

    @Enumerated(EnumType.STRING)
    private ImageType type;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User author;
    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post post;
    public void setPost(Post post) {
        this.post = post;
    }

    @Builder
    public Image(String url, ImageType type) {
        this.url = url;
        this.type = type;
    }
}
