package com.project.dasarang.domain.user.domain;

import com.project.dasarang.domain.post.domain.Post;
import com.project.dasarang.domain.upload.domain.Image;
import com.project.dasarang.domain.user.domain.enums.UserStatus;
import com.project.dasarang.domain.user.domain.enums.UserType;
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
@Table(name = "tb_user")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerNumber;

    private String email;

    private String userId;

    private String password;

    private String address;

    private String number;

    private String birth;

    @Enumerated(EnumType.STRING)
    private UserType authority;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Image> imageList;
    public void addImage(List<Image> images) {
        images.stream().map(image ->
                getImageList().add(image)
        ).close();
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> postList;
    public void addPost(Post post) {
        post.setAuthor(this);
        getPostList().add(post);
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? UserStatus.ACTIVE : this.status;
    }

    public void updateUser(String userId, String address, String number, String birth) {
        this.userId = userId;
        this.address = address;
        this.number = number;
        this.birth = birth;
    }

    public void deactivateUser() {
        this.status = UserStatus.DEACTIVATED;
    }

    @Builder
    public User(String ownerNumber, String email, String userId, String password, String address, String number, String birth, UserType authority) {
        this.ownerNumber = ownerNumber;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.address = address;
        this.number = number;
        this.birth = birth;
        this.authority = authority;
        this.postList = new ArrayList<>();
    }
}