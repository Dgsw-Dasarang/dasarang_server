package com.project.dasarang.global.infra.payment.dto.response;

import com.project.dasarang.global.infra.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentReturnResponse {

    private String requestedAt;
    private String approvedAt;
    private int price;
    private String receiptUrl;
    private String checkoutUrl;
    private String paymentKey;
    private String customerKey;
    private CardResponse card;

    public static PaymentReturnResponse of(PaymentResponse payment, String customerKey) {
        return PaymentReturnResponse.builder()
                .requestedAt(payment.getRequestedAt())
                .approvedAt(payment.getApprovedAt())
                .price(payment.getTotalAmount().intValue())
                .receiptUrl(payment.getReceipt().getUrl())
                .checkoutUrl(payment.getCheckout().getUrl())
                .paymentKey(payment.getPaymentKey())
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
                .paymentKey(this.paymentKey)
                .customerKey(this.customerKey)
                .build();
    }

}
