package com.project.dasarang.domain.admin.presentation;

import com.project.dasarang.domain.admin.service.UserAdminService;
import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.presentation.dto.response.UserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService userAdminService;

    @GetMapping("/user/list")
    public UserListResponse getUserList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return userAdminService.getUserList(page, size, UserType.ROLE_USER);
    }

    @GetMapping("/owner/list")
    public UserListResponse getOwnerList(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return userAdminService.getUserList(page, size, UserType.ROLE_OWNER);
    }

}
