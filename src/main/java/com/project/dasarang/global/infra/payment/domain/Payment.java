package com.project.dasarang.global.infra.payment.domain;

import com.project.dasarang.domain.user.domain.User;
import com.project.dasarang.global.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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

    private String requestedAt;
    private String approvedAt;
    private int price;
    private String receiptUrl;
    private String checkoutUrl;
    @Column(unique = true)
    private String paymentKey;
    private String customerKey;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cardList;
    public void addCard(Card card) {
        card.setPayment(this);
        getCardList().add(card);
    }

    @Builder
    public Payment(String requestedAt, String approvedAt, int price, String receiptUrl, String checkoutUrl,String paymentKey, String customerKey) {
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
        this.price = price;
        this.receiptUrl = receiptUrl;
        this.checkoutUrl = checkoutUrl;
        this.paymentKey = paymentKey;
        this.customerKey = customerKey;
        this.cardList = new ArrayList<>();
    }
}
