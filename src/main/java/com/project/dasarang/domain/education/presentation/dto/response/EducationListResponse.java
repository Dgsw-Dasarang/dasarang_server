package com.project.dasarang.domain.education.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class EducationListResponse {

    private int currentPage;
    private boolean hasMorePage;
    private List<EducationResponse> list;

}
