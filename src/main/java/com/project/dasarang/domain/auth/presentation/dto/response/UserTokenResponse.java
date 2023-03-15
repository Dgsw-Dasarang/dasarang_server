package com.project.dasarang.domain.auth.presentation.dto.response;

import com.project.dasarang.domain.auth.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class UserTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String name;
    private UserType type;

}
