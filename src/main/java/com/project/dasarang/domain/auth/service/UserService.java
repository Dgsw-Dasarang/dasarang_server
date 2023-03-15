package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.auth.presentation.dto.request.OwnerSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.UserTokenResponse;

public interface UserService {

    void userSignUp(UserSignUpRequest request);
    void ownerSignUp(OwnerSignUpRequest request);
    UserTokenResponse userSignIn(SignInRequest request);

}
