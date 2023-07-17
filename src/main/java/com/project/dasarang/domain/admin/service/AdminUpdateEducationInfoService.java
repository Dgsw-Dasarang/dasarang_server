package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.admin.presentation.dto.request.UpdateEducationInfoRequest;
import com.project.dasarang.domain.education.facade.EducationFacade;
import com.project.dasarang.global.infra.education.domain.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUpdateEducationInfoService {

    private final EducationFacade educationFacade;

    @Transactional
    public void execute(String number, UpdateEducationInfoRequest request) {
        Education education = educationFacade.findEducationByAcaAsnum(number);
        education.updateAdminEducation(request);
    }

}
