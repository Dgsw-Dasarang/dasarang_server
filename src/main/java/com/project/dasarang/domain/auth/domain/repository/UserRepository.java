package com.project.dasarang.domain.auth.domain.repository;

import com.project.dasarang.domain.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);

}
