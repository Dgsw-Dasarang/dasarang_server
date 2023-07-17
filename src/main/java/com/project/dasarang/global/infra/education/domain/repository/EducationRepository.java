package com.project.dasarang.global.infra.education.domain.repository;

import com.project.dasarang.global.infra.education.domain.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    boolean existsByAcaAsnum(String acaAsnum);

    Optional<Education> findByAcademyName(String academyName);

    Optional<Education> findByAcaAsnum(String acaAsnum);

    Page<Education> findAllByStatus(Pageable pageable, String status);

    @Query(value = "SELECT e FROM Education e WHERE e.acaAsnum  LIKE CONCAT('%', :content, '%')")
    Page<Education> findAllByAcaAsnum(@Param("content") String content, Pageable pageable);

    @Query(value = "SELECT e FROM Education e WHERE e.academyName  LIKE CONCAT('%', :content, '%')")
    Page<Education> findAllByAcademyName(@Param("content") String content, Pageable pageable);

    @Query(value = "SELECT e FROM Education e WHERE e.admstZoneName  LIKE CONCAT('%', :content, '%')")
    Page<Education> findAllByAdmstZoneName(@Param("content") String content, Pageable pageable);

}
