package com.project.dasarang.domain.post.domain;

import com.project.dasarang.domain.comment.domain.PostComment;
import com.project.dasarang.domain.post.presentation.dto.request.UpdatePostRequest;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_post")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User author;
    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> imageList;
    public void addImage(List<Image> image) {
        image.stream().map(it ->
            getImageList().add(it)
        ).close();
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> commentList;
    public void addComment(PostComment comment) {
        comment.setPost(this);
        this.commentList.add(comment);
    }

    public void modifyPost(UpdatePostRequest data) {
        this.title = data.getTitle();
        this.content = data.getContent();
    }

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.imageList = new ArrayList<>();
        this.commentList = new ArrayList<>();
    }
}
