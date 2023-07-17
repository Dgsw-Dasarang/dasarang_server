package com.project.dasarang.domain.education.service;

import com.project.dasarang.domain.education.facade.EducationFacade;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindEducationByAcaAsnumService {

    private final EducationFacade educationFacade;

    @Transactional
    public EducationResponse execute(String acaAsnum) {
        Education education = educationFacade.findEducationByAcaAsnum(acaAsnum);
        List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);

        return EducationResponse.of(education, tuitions);
    }

}
