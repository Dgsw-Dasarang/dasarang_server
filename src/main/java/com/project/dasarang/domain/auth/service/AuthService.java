package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.auth.presentation.dto.request.OwnerSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.TokenResponse;

public interface AuthService {

    void userSignUp(UserSignUpRequest request);
    void ownerSignUp(OwnerSignUpRequest request);
    TokenResponse signIn(SignInRequest request);
    String refreshAccessToken();

}
