package com.project.dasarang.domain.user.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;
import com.project.dasarang.global.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFacade userFacade;

    @Override
    @Transactional
    public UserResponse getUserInfo() {
        User user = userFacade.getCurrentUser();
        return ResponseUtil.getUserResponse(user);
    }
}
