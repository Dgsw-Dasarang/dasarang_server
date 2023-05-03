package com.project.dasarang.domain.payment.presentation;

import com.project.dasarang.domain.payment.presentation.dto.reqeust.IssueBillingRequest;
import com.project.dasarang.domain.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "결제 서버")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "정기 결제")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void payment(
            @RequestBody IssueBillingRequest request
    ) {
        paymentService.payment(request);
    }

}
