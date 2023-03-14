package com.project.dasarang.domain.auth.presentation.dto.request;

import com.project.dasarang.domain.auth.domain.User;
import com.project.dasarang.domain.auth.domain.enums.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    private String userId;
    private String password;
    private String address;
    private String number;
    private String birth;
    private UserType type;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .userId(this.userId).authority(this.type)
                .password(encodedPassword).address(this.address)
                .birth(this.birth).number(this.number)
                .build();
    }

}
