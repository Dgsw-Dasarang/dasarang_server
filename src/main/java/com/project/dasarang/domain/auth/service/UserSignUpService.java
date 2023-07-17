package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UserSignUpRequest request) {
        userFacade.existsByUserId(request.getUserId());
        User user = request.toEntity(passwordEncoder.encode(request.getPassword()));
        userFacade.save(user);
    }

}
