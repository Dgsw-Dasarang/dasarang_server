package com.project.dasarang.global.infra.payment.domain;

import com.project.dasarang.global.infra.payment.domain.enums.CardType;
import com.project.dasarang.global.infra.payment.domain.enums.OwnerType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_card")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String issuerCode;
    private String acquirerCode;
    private String number;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    private OwnerType ownerType;

    @ManyToOne
    @JoinColumn(name = "fk_payment")
    private Payment payment;
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Builder
    public Card(String issuerCode, String acquirerCode, String number, CardType cardType, OwnerType ownerType) {
        this.issuerCode = issuerCode;
        this.acquirerCode = acquirerCode;
        this.number = number;
        this.cardType = cardType;
        this.ownerType = ownerType;
    }
}
