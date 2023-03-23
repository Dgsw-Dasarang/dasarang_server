package com.project.dasarang.domain.user.domain.repository;

import com.project.dasarang.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);

}
