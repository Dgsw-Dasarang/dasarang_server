package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateOwnerInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.response.UserListResponse;

public interface UserAdminService {

    UserListResponse getUserList(int page, int size, UserType type);
    void approveOwner(Long id);

    void updateUserInfo(Long id, UpdateUserInfoRequest request);
    void updateOwnerInfo(Long id, UpdateOwnerInfoRequest request);
    void deleteUser(Long id);
    void deleteOwner(Long id);

}
