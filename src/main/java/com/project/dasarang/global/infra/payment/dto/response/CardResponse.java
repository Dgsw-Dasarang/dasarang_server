package com.project.dasarang.global.infra.payment.dto.response;

import com.project.dasarang.global.infra.payment.domain.Card;
import com.project.dasarang.global.infra.payment.domain.enums.CardType;
import com.project.dasarang.global.infra.payment.domain.enums.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
public class CardResponse implements Serializable {

    private Number amount;
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String installmentPlanMonths;
    private String approveNo;
    private boolean useCardPoint;
    private String cardType;
    private String ownerType;
    private String acquireStatus;
    private boolean isInterestFree;
    private String interestPayer;

    public Card toEntity() {
        return Card.builder()
                .issuerCode(this.issuerCode)
                .acquirerCode(this.acquirerCode)
                .number(this.number)
                .cardType(this.cardType.equals("신용") ? CardType.CREDIT :
                        this.cardType.equals("체크") ? CardType.CHECK : CardType.GIFT)
                .ownerType(this.ownerType.equals("개인") ? OwnerType.PERSONAL : OwnerType.CORPORATION)
                .build();
    }

}
