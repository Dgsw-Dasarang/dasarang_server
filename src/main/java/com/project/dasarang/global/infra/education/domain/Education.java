package com.project.dasarang.global.infra.education.domain;

import com.project.dasarang.domain.admin.presentation.dto.request.UpdateEducationInfoRequest;
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

    // 학원 지정번호
    @Column(unique = true)
    private String acaAsnum;

    // 행정 교육명
    private String admstZoneName;

    // 학원명
    private String academyName;

    // 개설 일자
    private String createdAt;

    // 등록 상태
    private String status;

    // 정원합계
    private int totalSeats;

    // 교습과정명
    private String courseName;

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

    public void updateAdminEducation(UpdateEducationInfoRequest data) {
        this.admstZoneName = data.getZoneName() == null ? this.admstZoneName : data.getZoneName();
        this.academyName = data.getAcademyName() == null ? this.academyName : data.getAcademyName();
        this.status = data.getStatus() == null ? this.status : data.getStatus();
        this.totalSeats = data.getTotalSeats();
        this.courseName = data.getCourseName() == null ? this.courseName : data.getCourseName();
        this.roadAddress = data.getRoadAddress() == null ? this.roadAddress : data.getRoadAddress();
    }

    public void updateEducation(String admstZoneName, String academyName, String createdAt, String status, int totalSeats,
                                String courseName, String roadAddress) {
        this.admstZoneName = admstZoneName;
        this.academyName = academyName;
        this.createdAt = createdAt;
        this.status = status;
        this.totalSeats = totalSeats;
        this.courseName = courseName;
        this.roadAddress = roadAddress;
    }

    @Builder
    public Education(String acaAsnum, String admstZoneName, String academyName, String createdAt, String status,
                     int totalSeats, String courseName, String roadAddress) {
        this.acaAsnum = acaAsnum;
        this.admstZoneName = admstZoneName;
        this.academyName = academyName;
        this.createdAt = createdAt;
        this.status = status;
        this.totalSeats = totalSeats;
        this.courseName = courseName;
        this.tuitionList = new ArrayList<>();
        this.roadAddress = roadAddress;
    }
}
