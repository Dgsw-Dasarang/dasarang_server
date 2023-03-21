package com.project.dasarang.domain.education.service;

import com.project.dasarang.domain.education.facade.EducationFacade;
import com.project.dasarang.domain.education.presentation.dto.response.EducationListResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import com.project.dasarang.global.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService{

    private final EducationFacade educationFacade;

    @Override
    public EducationResponse getEducationByAcaName(String academyName) {
        Education education = educationFacade.findEducationByAcademyName(academyName);
        List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);

        return ResponseUtil.getEducationResponse(education, tuitions);
    }

    @Override
    public EducationListResponse getEducationAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Education> educations = educationFacade.findEducationAll(pageable);

        List<EducationResponse> responses = educations.map(education -> {
            List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);
            return ResponseUtil.getEducationResponse(education, tuitions);
        }).stream().collect(Collectors.toList());

        return new EducationListResponse(responses);
    }

    @Override
    public EducationListResponse getEducationAllByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Education> educations = educationFacade.findEducationAllByStatus(pageable, status);
        List<EducationResponse> responses = educations.map(education -> {
            List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);
            return ResponseUtil.getEducationResponse(education, tuitions);
        }).stream().collect(Collectors.toList());

        return new EducationListResponse(responses);
    }
}
