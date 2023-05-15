package com.project.dasarang.global.infra.payment.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PaymentResponse implements Serializable {

    private String version;
    private String paymentKey;
    private String type;
    private String orderId;
    private String orderName;
    private String mId;
    private String currency;
    private String method;
    private Number totalAmount;
    private Number balanceAmount;
    private String status;
    private String requestedAt;
    private String approvedAt;
    private boolean useEscrow;
    private String lastTransactionKey;
    private Number suppliedAmount;
    private Number vat;
    private boolean cultureExpense;
    private Number taxFreeAmount;
    private Integer taxExemptionAmount;
    private CancelResponse[] cancels;
    private boolean isPartialCancelable;
    private CardResponse card;
    private VirtualAccountResponse virtualAccount;
    private String secret;
    private MobilePhoneResponse mobilePhone;
    private GiftCertificateResponse giftCertificate;
    private TransferResponse transfer;
    private ReceiptResponse receipt;
    private CheckOutResponse checkout;
    private EasyPayResponse easyPay;
    private String country;
    private FailureResponse failure;
    private CashReceiptResponse cashReceipt;
    private CashReceiptsResponse[] cashReceipts;
    private DiscountResponse discount;

}
