package com.project.dasarang.domain.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_OWNER("OWNER");

    private final String role;

}
