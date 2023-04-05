package com.project.dasarang.domain.education.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class EducationResponse {

    private String admstZoneName;
    private String academyName;
    private String createdAt;
    private String status;
    private int totalSeats;
    private String courseName;
    private List<TuitionResponse> tuitions;
    private String roadAddress;

}
