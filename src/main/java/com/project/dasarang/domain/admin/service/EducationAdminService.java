package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.admin.presentation.dto.request.UpdateEducationInfoRequest;

public interface EducationAdminService {

    void updateEducationInfo(String number, UpdateEducationInfoRequest request);

}
