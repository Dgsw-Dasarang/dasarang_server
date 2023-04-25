package com.project.dasarang.global.infra.payment.domain;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.entity.BaseTime;
import com.project.dasarang.global.infra.payment.domain.enums.PayType;
import com.project.dasarang.global.infra.payment.domain.enums.PaymentStatus;
import com.project.dasarang.global.infra.payment.domain.enums.PaymentType;
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
@Table(name = "tb_payment")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Builder
    public Payment(PaymentType type, PaymentStatus status, PayType payType) {
        this.type = type;
        this.status = status;
        this.payType = payType;
    }
}
