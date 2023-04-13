package com.project.dasarang.domain.admin.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateEducationInfoRequest {

    private String zoneName;
    private String academyName;
    private String status;
    private int totalSeats;
    private String courseName;
    private String roadAddress;

}
