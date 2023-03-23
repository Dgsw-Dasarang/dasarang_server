package com.project.dasarang.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class UserResponse {

    private String userId;
    private String password;
    private String address;
    private String number;
    private String type;

}
