package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserType;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.domain.user.presentation.dto.response.UserListResponse;
import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminFindAllUserService {

    private final UserFacade userFacade;

    @Transactional
    public UserListResponse execute(int page, int size, UserType type) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");

        Page<User> users = userFacade.findAllUser(pageable, type);
        List<UserResponse> list = users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());

        return UserListResponse.builder()
                .currentPage(users.getNumber() + 1)
                .hasMorePage(users.getTotalPages() > users.getNumber())
                .list(list)
                .build();
    }

}
