package com.project.dasarang.domain.education.service;

import com.project.dasarang.global.infra.education.domain.enums.EducationCategory;
import com.project.dasarang.domain.education.facade.EducationFacade;
import com.project.dasarang.domain.education.presentation.dto.response.EducationListResponse;
import com.project.dasarang.domain.education.presentation.dto.response.EducationResponse;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationFacade educationFacade;

    @Transactional
    public EducationResponse getEducationByAcaAsnum(String acaAsnum) {
        Education education = educationFacade.findEducationByAcaAsnum(acaAsnum);
        List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);

        return EducationResponse.of(education, tuitions);
    }

    @Transactional
    public EducationResponse getEducationByAcaName(String academyName) {
        Education education = educationFacade.findEducationByAcademyName(academyName);
        List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);

        return EducationResponse.of(education, tuitions);
    }

    @Transactional
    public EducationListResponse getEducationAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Education> educations = educationFacade.findEducationAll(pageable);

        List<EducationResponse> responses = educations.stream().map(education -> {
            List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);
            return EducationResponse.of(education, tuitions);
        }).collect(Collectors.toList());

        return EducationListResponse.builder()
                .currentPage(educations.getNumber() + 1)
                .hasMorePage(educations.getTotalPages() > educations.getNumber())
                .list(responses)
                .build();
    }

    @Transactional
    public EducationListResponse getEducationByCategory(EducationCategory category, String content, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Education> educations = null;
        if (category.equals(EducationCategory.NUMBER))
            educations = educationFacade.findEducationAllByAcaAsnum(pageable, content);
        else if (category.equals(EducationCategory.NAME))
            educations = educationFacade.findEducationAllByAcademyName(pageable, content);
        else if (category.equals(EducationCategory.ZONE_NAME))
            educations = educationFacade.findEducationAllByAdmstZoneName(pageable, content);

        List<EducationResponse> responses = educations.stream().map(education -> {
            List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);
            return EducationResponse.of(education, tuitions);
        }).collect(Collectors.toList());

        return EducationListResponse.builder()
                .currentPage(educations.getNumber() + 1)
                .hasMorePage(educations.getTotalPages() > educations.getNumber())
                .list(responses)
                .build();
    }

    @Transactional
    public EducationListResponse getEducationAllByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Education> educations = educationFacade.findEducationAllByStatus(pageable, status);
        List<EducationResponse> responses = educations.stream().map(education -> {
            List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);
            return EducationResponse.of(education, tuitions);
        }).collect(Collectors.toList());

        return EducationListResponse.builder()
                .currentPage(educations.getNumber() + 1)
                .hasMorePage(educations.getTotalPages() > educations.getNumber())
                .list(responses)
                .build();
    }
}
