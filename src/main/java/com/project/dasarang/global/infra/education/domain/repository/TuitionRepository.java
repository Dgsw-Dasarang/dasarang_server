package com.project.dasarang.global.infra.education.domain.repository;

import com.project.dasarang.global.infra.education.domain.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionRepository extends JpaRepository<Tuition, Long> {
}
