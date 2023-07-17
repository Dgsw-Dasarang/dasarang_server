package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.enums.UserStatus;
import com.project.dasarang.domain.user.exception.UserAlreadyActiveException;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminApproveOwnerService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(Long id) {
        User user = userFacade.findUserById(id);

        if(user.getStatus().equals(UserStatus.ACTIVE))
            throw UserAlreadyActiveException.EXCEPTION;

        user.setStatus(UserStatus.ACTIVE);
    }

}
