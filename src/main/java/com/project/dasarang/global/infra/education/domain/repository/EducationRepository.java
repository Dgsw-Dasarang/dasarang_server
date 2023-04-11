package com.project.dasarang.global.infra.education.domain.repository;

import com.project.dasarang.global.infra.education.domain.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    boolean existsByAcademyName(String academyName);

    Optional<Education> findByAcademyName(String academyName);

    Optional<Education> findByAcaAsnum(String acaAsnum);

    Page<Education> findAllByStatus(Pageable pageable, String status);

    @Query("SELECT e FROM Education e WHERE :category LIKE CONCAT('%', :content, '%')")
    Page<Education> findByCategory(Pageable pageable, String category, String content);

}
