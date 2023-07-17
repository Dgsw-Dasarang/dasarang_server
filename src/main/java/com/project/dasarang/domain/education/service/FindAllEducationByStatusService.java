package com.project.dasarang.domain.education.service;

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
public class FindAllEducationByStatusService {

    private final EducationFacade educationFacade;

    @Transactional
    public EducationListResponse execute(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Education> educations = educationFacade.findEducationAllByStatus(pageable, status);

        List<EducationResponse> list = educations.stream()
                .map(education -> {
                    List<Tuition> tuitions = educationFacade.findTuitionAllByEducation(education);
                    return EducationResponse.of(education, tuitions);
                }).collect(Collectors.toList());

        return EducationListResponse.builder()
                .currentPage(educations.getNumber() + 1)
                .hasMorePage(educations.getTotalPages() > educations.getNumber())
                .list(list)
                .build();
    }

}
