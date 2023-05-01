package com.project.dasarang.global.infra.education.domain.repository;

import com.amazonaws.transform.SimpleTypeIonUnmarshallers;
import com.project.dasarang.global.infra.education.domain.Education;
import com.project.dasarang.global.infra.education.domain.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TuitionRepository extends JpaRepository<Tuition, Long> {

    List<Tuition> findAllByEducation(Education education);

    boolean existsByTitle(String title);

    void deleteAllByTitle(String title);

    Optional<Tuition> findByTitle(String title);
}
