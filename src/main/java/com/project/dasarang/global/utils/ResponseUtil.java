package com.project.dasarang.global.utils;

import com.project.dasarang.domain.auth.domain.User;
import com.project.dasarang.domain.auth.presentation.dto.response.UserResponse;

public class ResponseUtil {

    public static UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId()).password(user.getPassword())
                .address(user.getAddress()).number(user.getNumber())
                .type(user.getAuthority().getRole())
                .build();
    }

}
