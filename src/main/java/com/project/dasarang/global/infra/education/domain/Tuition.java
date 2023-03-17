package com.project.dasarang.global.infra.education.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "tb_tuition")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Tuition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tuitionId;

    @ManyToOne
    @JoinColumn(name = "fk_education")
    private Education education;
    public void setEducation(Education education) {
        this.education = education;
    }

    private String title;
    private int price;

    @Builder
    public Tuition(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
