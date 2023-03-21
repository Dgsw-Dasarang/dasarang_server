package com.project.dasarang.global.infra.education.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_education")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;

    // 행정 교육명
    private String admstZoneName;

    // 학원명
    @Column(unique = true)
    private String academyName;

    // 개설 일자
    private String createdAt;

    // 등록 상태
    private String status;

    // 정원합계
    private int totalSeats;

    // 교습과정목록명
    private String courseListName;

    // 인당수강료 내용
    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tuition> tuitionList;
    public void addTuition(List<Tuition> tuitions) {
        for (Tuition tuition : tuitions) {
            tuition.setEducation(this);
            getTuitionList().add(tuition);
        }
    }

    // 도로명 주소
    private String roadAddress;

    public void updateEducation(String admstZoneName, String academyName, String createdAt, String status, int totalSeats, String courseListName, String roadAddress) {
        this.admstZoneName = admstZoneName;
        this.academyName = academyName;
        this.createdAt = createdAt;
        this.status = status;
        this.totalSeats = totalSeats;
        this.courseListName = courseListName;
        this.roadAddress = roadAddress;
    }

    @Builder
    public Education(String admstZoneName, String academyName, String createdAt, String status, int totalSeats, String courseListName, String roadAddress) {
        this.admstZoneName = admstZoneName;
        this.academyName = academyName;
        this.createdAt = createdAt;
        this.status = status;
        this.totalSeats = totalSeats;
        this.courseListName = courseListName;
        this.tuitionList = new ArrayList<>();
        this.roadAddress = roadAddress;
    }
}
