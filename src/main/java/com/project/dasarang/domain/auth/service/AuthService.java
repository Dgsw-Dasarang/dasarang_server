package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.repository.UserRepository;
import com.project.dasarang.domain.user.exception.PasswordWrongException;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.domain.auth.presentation.dto.request.OwnerSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.TokenResponse;
import com.project.dasarang.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;

    @Transactional
    public void userSignUp(UserSignUpRequest request) {
        userFacade.existsByUserId(request.getUserId());
        User user = request.toEntity(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void ownerSignUp(OwnerSignUpRequest request) {
        userFacade.existsByUserId(request.getUserId());
        User user = request.toEntity(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public TokenResponse signIn(SignInRequest request) {
        User user = userFacade.findUserByUserId(request.getUserId());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        return TokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getUserId(), user.getAuthority()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getUserId(), user.getAuthority()))
                .name(user.getUserId()).type(user.getAuthority()).status(user.getStatus())
                .build();
    }

    @Transactional
    public String refreshAccessToken() {
        User user = userFacade.getCurrentUser();
        
        return jwtProvider.generateAccessToken(user.getUserId(), user.getAuthority());
    }
}
