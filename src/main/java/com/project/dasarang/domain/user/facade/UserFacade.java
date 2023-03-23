package com.project.dasarang.domain.user.facade;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.repository.UserRepository;
import com.project.dasarang.domain.user.exception.UserAlreadyExistsException;
import com.project.dasarang.domain.user.exception.UserNotFoundException;
import com.project.dasarang.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return auth.getUser();
    }

    @Transactional(readOnly = true)
    public void existsByUserId(String userId) {
        if(userRepository.existsByUserId(userId))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Transactional(readOnly = true)
    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
