package com.project.dasarang.domain.auth.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshAccessTokenService {

    private final UserFacade userFacade;
    private final JwtProvider jwtProvider;

    @Transactional
    public String execute() {
        User user = userFacade.getCurrentUser();

        return jwtProvider.generateAccessToken(user.getUserId(), user.getAuthority());
    }

}
