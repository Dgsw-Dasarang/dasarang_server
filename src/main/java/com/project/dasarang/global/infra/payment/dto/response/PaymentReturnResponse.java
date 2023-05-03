package com.project.dasarang.global.infra.payment.dto.response;

import com.project.dasarang.global.infra.payment.domain.Payment;
import com.project.dasarang.global.infra.payment.domain.enums.CardType;
import com.project.dasarang.global.infra.payment.domain.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter @Builder
@AllArgsConstructor
public class PaymentReturnResponse {

    private String requestedAt;
    private String approvedAt;
    private int price;
    private String receiptUrl;
    private String checkoutUrl;
    private String customerKey;
    private CardResponse card;

    public static PaymentReturnResponse of(PaymentResponse payment, String customerKey) {
        return PaymentReturnResponse.builder()
                .requestedAt(payment.getRequestedAt())
                .approvedAt(payment.getApprovedAt())
                .price(payment.getTotalAmount().intValue())
                .receiptUrl(payment.getReceipt().getUrl())
                .checkoutUrl(payment.getCheckout().getUrl())
                .customerKey(customerKey)
                .card(payment.getCard())
                .build();
    }

    public Payment toEntity() {
        return Payment.builder()
                .requestedAt(this.requestedAt)
                .approvedAt(this.approvedAt)
                .price(this.price)
                .receiptUrl(this.receiptUrl)
                .checkoutUrl(this.checkoutUrl)
                .customerKey(this.customerKey)
                .build();
    }

}
