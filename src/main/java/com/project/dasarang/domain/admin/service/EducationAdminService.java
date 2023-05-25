package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.admin.presentation.dto.request.UpdateEducationInfoRequest;
import com.project.dasarang.domain.education.facade.EducationFacade;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EducationAdminService {

    private final UserFacade userFacade;
    private final EducationFacade educationFacade;
    private final EducationRepository educationRepository;


    @Transactional
    public void updateEducationInfo(String number, UpdateEducationInfoRequest request) {
        Education education = educationFacade.findEducationByAcaAsnum(number);
        education.updateAdminEducation(request);

        educationRepository.save(education);
    }
}
