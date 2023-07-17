package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUpdateUserInfoService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(Long id, UpdateUserInfoRequest request) {
        User user = userFacade.findUserById(id);
        user.updateUser(request);
    }

}
