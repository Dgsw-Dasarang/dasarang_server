package com.project.dasarang.domain.post.domain;

import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "tb_post")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;



}
