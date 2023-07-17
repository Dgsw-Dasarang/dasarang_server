package com.project.dasarang.domain.user.presentation;

import com.project.dasarang.domain.user.presentation.dto.request.UpdateOwnerInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;
import com.project.dasarang.domain.user.service.FindUserInfoService;
import com.project.dasarang.domain.user.service.UpdateOwnerInfoService;
import com.project.dasarang.domain.user.service.UpdateUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "유저 서버")
public class UserController {

    private final FindUserInfoService findUserInfoService;
    private final UpdateUserInfoService updateUserInfoService;
    private final UpdateOwnerInfoService updateOwnerInfoService;

    @Operation(summary = "유저 정보 조회")
    @GetMapping("")
    public UserResponse getUserInfo() {
        return findUserInfoService.execute();
    }

    @Operation(summary = "유저 데이터 변경")
    @PatchMapping("/user")
    public void updateUserInfo(
            @RequestBody UpdateUserInfoRequest request
    ) {
        updateUserInfoService.execute(request);
    }

    @Operation(summary = "업주 데이터 변경")
    @PatchMapping("/onwer")
    public void updateOnwerInfo(
            @RequestBody UpdateOwnerInfoRequest request
    ) {
        updateOwnerInfoService.execute(request);
    }
}
