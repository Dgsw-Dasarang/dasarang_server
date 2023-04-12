package com.project.dasarang.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateOwnerInfoRequest {

    private String userId;
    private String address;
    private String number;
    private String birth;
    private String email;

}
