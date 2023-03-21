package com.project.dasarang.domain.education.service;

import com.project.dasarang.domain.education.presentation.dto.response.EducationListResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;

public interface EducationService {

    EducationResponse getEducationByAcaName(String academyName);

    EducationListResponse getEducationAll(int page, int size);

    EducationListResponse getEducationAllByStatus(String status, int page, int size);

}
