package com.project.dasarang.global.infra.payment.domain.repository;

import com.project.dasarang.global.infra.payment.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumber(String number);

}
