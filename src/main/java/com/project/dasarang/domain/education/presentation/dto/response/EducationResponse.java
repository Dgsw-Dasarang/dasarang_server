package com.project.dasarang.domain.education.presentation.dto.response;

import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter @Builder
@AllArgsConstructor
public class EducationResponse {

    private String academyNumber;
    private String admstZoneName;
    private String academyName;
    private String createdAt;
    private String status;
    private int totalSeats;
    private String courseName;
    private List<TuitionResponse> tuitions;
    private String roadAddress;

    public static EducationResponse of(Education education, List<Tuition> tuitions) {
        return EducationResponse.builder()
                .academyNumber(education.getAcaAsnum())
                .admstZoneName(education.getAdmstZoneName())
                .academyName(education.getAcademyName())
                .createdAt(education.getCreatedAt())
                .status(education.getStatus())
                .totalSeats(education.getTotalSeats())
                .courseName(education.getCourseName())
                .tuitions(tuitions != null ? tuitions.stream().map(
                        TuitionResponse::of
                ).collect(Collectors.toList()) : null)
                .roadAddress(education.getRoadAddress())
                .build();
    }

}
