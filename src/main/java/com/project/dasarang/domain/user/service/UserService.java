package com.project.dasarang.domain.user.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.domain.repository.UserRepository;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateOwnerInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import com.project.dasarang.domain.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Transactional
    public UserResponse getUserInfo() {
        User user = userFacade.getCurrentUser();
        return UserResponse.of(user);
    }

    @Transactional
    public void updateUserInfo(UpdateUserInfoRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateUser(request);

        userRepository.save(user);
    }

    @Transactional
    public void updateOwnerInfo(UpdateOwnerInfoRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateOwner(request);

        userRepository.save(user);
    }

}
