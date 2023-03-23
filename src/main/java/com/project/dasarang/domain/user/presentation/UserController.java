package com.project.dasarang.domain.user.presentation;

import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;
import com.project.dasarang.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "유저 서버")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 정보 조회")
    @GetMapping
    public UserResponse getUserInfo() {
        return userService.getUserInfo();
    }

}
