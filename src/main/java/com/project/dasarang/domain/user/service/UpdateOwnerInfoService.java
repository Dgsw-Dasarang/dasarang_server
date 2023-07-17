package com.project.dasarang.domain.user.service;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import com.project.dasarang.domain.user.presentation.dto.request.UpdateOwnerInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOwnerInfoService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateOwnerInfoRequest request) {
        User user = userFacade.getCurrentUser();
        user.updateOwner(request);
    }

}
