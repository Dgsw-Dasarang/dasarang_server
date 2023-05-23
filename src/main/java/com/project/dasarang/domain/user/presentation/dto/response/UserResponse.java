package com.project.dasarang.domain.user.presentation.dto.response;

import com.project.dasarang.domain.user.domain.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String userId;
    private String address;
    private String number;
    private String type;
    private String ownerNumber;
    private String email;
    private String birth;
    private UserStatus status;

}
