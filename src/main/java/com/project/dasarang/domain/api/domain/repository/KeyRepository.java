package com.project.dasarang.domain.api.domain.repository;

import com.project.dasarang.domain.api.domain.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeyRepository extends JpaRepository<Key, String> {

    Optional<Key> findByNameAndNumber(String name, String number);

    Optional<Key> findByApiKey(String key);

}
