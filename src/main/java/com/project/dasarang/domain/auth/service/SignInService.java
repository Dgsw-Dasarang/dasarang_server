package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.TokenResponse;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.exception.PasswordWrongException;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInService {

    private final UserFacade userFacade;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(SignInRequest request) {
        User user = userFacade.findUserByUserId(request.getUserId());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getUserId(), user.getAuthority()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getUserId(), user.getAuthority()))
                .name(user.getUserId()).type(user.getAuthority()).status(user.getStatus())
                .ownerNumber(user.getOwnerNumber())
                .build();
    }

}
