package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.auth.presentation.dto.request.SignInRequest;
import com.project.dasarang.domain.auth.presentation.dto.request.SignUpRequest;
import com.project.dasarang.domain.auth.presentation.dto.response.UserTokenResponse;

public interface UserService {

    void userSignUp(SignUpRequest request);
    UserTokenResponse userSignIn(SignInRequest request);

}
