package com.project.dasarang.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
@AllArgsConstructor
public class UserListResponse {

    private int currentPage;
    private boolean hasMorePage;
    private List<UserResponse> list;

}
