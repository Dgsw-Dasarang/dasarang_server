package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.auth.domain.User;
import com.project.dasarang.domain.auth.domain.repository.UserRepository;
import com.project.dasarang.domain.auth.exception.PasswordWrongException;
import com.project.dasarang.domain.auth.facade.UserFacade;
import com.project.dasarang.domain.auth.presentation.dto.request.OwnerSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.UserTokenResponse;
import com.project.dasarang.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;

    @Override
    @Transactional
    public void userSignUp(UserSignUpRequest request) {
        userFacade.existsByUserId(request.getUserId());
        User user = request.toEntity(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void ownerSignUp(OwnerSignUpRequest request) {
        userFacade.existsByUserId(request.getUserId());
        User user = request.toEntity(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserTokenResponse userSignIn(SignInRequest request) {
        User user = userFacade.findUserByUserId(request.getUserId());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        return UserTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getUserId(), user.getAuthority()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getUserId(), user.getAuthority()))
                .name(user.getUserId()).type(user.getAuthority())
                .build();

    }
}
