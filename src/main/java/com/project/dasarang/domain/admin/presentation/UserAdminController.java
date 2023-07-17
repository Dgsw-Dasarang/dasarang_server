package com.project.dasarang.domain.admin.presentation;

import com.project.dasarang.domain.admin.service.AdminApproveOwnerService;
import com.project.dasarang.domain.admin.service.AdminFindAllUserService;
import com.project.dasarang.domain.admin.service.AdminUpdateOwnerInfoService;
import com.project.dasarang.domain.admin.service.AdminUpdateUserInfoService;
import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateOwnerInfoRequest;
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

    private final AdminFindAllUserService adminFindAllUserService;
    private final AdminApproveOwnerService adminApproveOwnerService;
    private final AdminUpdateUserInfoService adminUpdateUserInfoService;
    private final AdminUpdateOwnerInfoService adminUpdateOwnerInfoService;

    @Operation(summary = "유저 리스트 조회")
    @GetMapping("/user/list")
    public UserListResponse getUserList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return adminFindAllUserService.execute(page, size, UserType.ROLE_USER);
    }

    @Operation(summary = "학원장 리스트 조회")
    @GetMapping("/owner/list")
    public UserListResponse getOwnerList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return adminFindAllUserService.execute(page, size, UserType.ROLE_OWNER);
    }

    @Operation(summary = "업주 승인")
    @PatchMapping("/owner/approve/{id}")
    public void approveOwner(
            @PathVariable("id") Long id
    ) {
        adminApproveOwnerService.execute(id);
    }

    @Operation(summary = "유저 프로필 수정")
    @PatchMapping("/user/update/{id}")
    public void updateUser(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserInfoRequest request
    ) {
        adminUpdateUserInfoService.execute(id, request);
    }

    @Operation(summary = "학원 프로필 수정")
    @PatchMapping("/owner/update/{id}")
    public void updateOwner(
            @PathVariable("id") Long id,
            @RequestBody UpdateOwnerInfoRequest request
    ) {
        adminUpdateOwnerInfoService.execute(id, request);
    }

}
