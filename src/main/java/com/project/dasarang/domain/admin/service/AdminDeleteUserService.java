package com.project.dasarang.domain.admin.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDeleteUserService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(Long id) {
        User user = userFacade.findUserById(id);

        userFacade.delete(user);
    }

}
