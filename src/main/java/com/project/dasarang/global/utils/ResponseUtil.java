package com.project.dasarang.global.utils;

import com.project.dasarang.domain.auth.domain.User;
import com.project.dasarang.domain.auth.presentation.dto.response.UserResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationListResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.domain.education.presentation.dto.response.TuitionResponse;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {

    public static UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId()).password(user.getPassword())
                .address(user.getAddress()).number(user.getNumber())
                .type(user.getAuthority().getRole())
                .build();
    }

    public static EducationResponse getEducationResponse(Education education, List<Tuition> tuition) {
        return EducationResponse.builder()
                .admstZoneName(education.getAdmstZoneName())
                .academyName(education.getAcademyName())
                .createdAt(education.getCreatedAt())
                .status(education.getStatus())
                .totalSeats(education.getTotalSeats())
                .courseListName(education.getCourseListName())
                .tuitions(tuition != null ? tuition.stream().map(ResponseUtil::getTuitionResponse).collect(Collectors.toList()) : null)
                .roadAddress(education.getRoadAddress())
                .build();
    }

    public static TuitionResponse getTuitionResponse(Tuition tuition) {
        return TuitionResponse.builder()
                .title(tuition.getTitle())
                .price(tuition.getPrice())
                .build();
    }

}
