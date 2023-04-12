package com.project.dasarang.domain.user.service;

import com.project.dasarang.domain.user.presentation.dto.request.UpdateOwnerInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;

public interface UserService {

    UserResponse getUserInfo();

    void updateUserInfo(UpdateUserInfoRequest request);

    void updateOwnerInfo(UpdateOwnerInfoRequest request);

}
