package com.project.dasarang.domain.admin.presentation;

import com.project.dasarang.domain.admin.service.UserAdminService;
import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.response.UserListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "유저 관리 서버")
public class UserAdminController {

    private final UserAdminService userAdminService;

    @Operation(summary = "유저 리스트 조회")
    @GetMapping("/user/list")
    public UserListResponse getUserList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return userAdminService.getUserList(page, size, UserType.ROLE_USER);
    }

    @Operation(summary = "학원 리스트 조회")
    @GetMapping("/owner/list")
    public UserListResponse getOwnerList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return userAdminService.getUserList(page, size, UserType.ROLE_OWNER);
    }

    @Operation(summary = "업주 승인")
    @PatchMapping("/owner/approve/{id}")
    public void approveOwner(
            @PathVariable("id") Long id
    ) {
        userAdminService.approveOwner(id);
    }

    @Operation(summary = "유저 프로필 수정")
    @PatchMapping("/user/update/{id}")
    public void updateUser(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserInfoRequest request
    ) {
        userAdminService.updateUserInfo(id, request);
    }

}
