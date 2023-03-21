package com.project.dasarang.domain.education.facade;

import com.project.dasarang.domain.education.exception.EducationNotFoundException;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import com.project.dasarang.global.infra.education.domain.repository.EducationRepository;
import com.project.dasarang.global.infra.education.domain.repository.TuitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationFacade {

    private final EducationRepository educationRepository;
    private final TuitionRepository tuitionRepository;

    @Transactional(readOnly = true)
    public Education findEducationByAcademyName(String academyName) {
        return educationRepository.findByAcademyName(academyName)
                .orElseThrow(() -> EducationNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public List<Tuition> findTuitionAllByEducation(Education education) {
        return tuitionRepository.findAllByEducation(education);
    }

    @Transactional(readOnly = true)
    public Page<Education> findEducationAll(Pageable pageable) {
        return educationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Education> findEducationAllByStatus(Pageable pageable, String status) {
        return educationRepository.findAllByStatus(pageable, status);
    }

}
