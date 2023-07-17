package com.project.dasarang.domain.payment.service;

import com.project.dasarang.domain.payment.presentation.dto.response.CheckPaymentResponse;
import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckPaymentService {

    private final UserFacade userFacade;

    public CheckPaymentResponse execute() {
        User user = userFacade.getCurrentUser();

        boolean confirm = user.getCustomerKey() != null;

        return new CheckPaymentResponse(user.getUserId(), confirm);
    }

}
