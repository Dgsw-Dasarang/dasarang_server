package com.project.dasarang.global.infra.education.domain.repository;

import com.project.dasarang.global.infra.education.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
