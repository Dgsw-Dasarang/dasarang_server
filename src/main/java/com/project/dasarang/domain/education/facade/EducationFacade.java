package com.project.dasarang.domain.education.facade;

import com.project.dasarang.global.infra.education.domain.enums.EducationCategory;
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
@Transactional(readOnly = true)
public class EducationFacade {

    private final EducationRepository educationRepository;
    private final TuitionRepository tuitionRepository;

    public Education findEducationByAcaAsnum(String acaAsnum) {
        return educationRepository.findByAcaAsnum(acaAsnum)
                .orElseThrow(() -> EducationNotFoundException.EXCEPTION);
    }

    public Education findEducationByAcademyName(String academyName) {
        return educationRepository.findByAcademyName(academyName)
                .orElseThrow(() -> EducationNotFoundException.EXCEPTION);
    }

    public List<Tuition> findTuitionAllByEducation(Education education) {
        return tuitionRepository.findAllByEducation(education);
    }

    public Page<Education> findEducationAll(Pageable pageable) {
        return educationRepository.findAll(pageable);
    }

    public Page<Education> findEducationByCategory(EducationCategory category, Pageable pageable, String content) {
        if (category.equals(EducationCategory.NUMBER))
            return educationRepository.findAllByAcaAsnum(content, pageable);
        else if (category.equals(EducationCategory.NAME))
            return educationRepository.findAllByAcademyName(content, pageable);
        else if (category.equals(EducationCategory.ZONE_NAME))
            return educationRepository.findAllByAdmstZoneName(content, pageable);

        return null;
    }

    public Page<Education> findEducationAllByStatus(Pageable pageable, String status) {
        return educationRepository.findAllByStatus(pageable, status);
    }

}
