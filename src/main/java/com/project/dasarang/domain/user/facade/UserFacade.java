package com.project.dasarang.domain.user.facade;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.domain.repository.UserRepository;
import com.project.dasarang.domain.user.exception.UserAlreadyExistsException;
import com.project.dasarang.domain.user.exception.UserNotFoundException;
import com.project.dasarang.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        AuthDetails auth = (AuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findUserById(auth.getUser().getId());
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

    @Transactional(readOnly = true)
    public Page<User> findAllUser(Pageable pageable, UserType type) {
        return userRepository.findAllByAuthority(pageable, type);
    }

    @Transactional(readOnly = true)
    public User findUserByAcaAsnum(String acaAsnum) {
        return userRepository.findByOwnerNumber(acaAsnum)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

}
