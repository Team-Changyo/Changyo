package com.shinhan.changyo.domain.trade;

import com.shinhan.changyo.domain.TimeBaseEntity;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.qrcode.QrCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 보증금 거래내역 엔티티
 * 
 * @author 최영환
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "deposit_trade")
public class Trade extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_trade_id")
    private Long id;
    @Column(nullable = false, length = 100)
    private String summary;
    @Column(nullable = false)
    private int withdrawalAmount;
    @Column(nullable = false)
    private int depositAmount;
    @Column(nullable = false, length = 100)
    private String content;
    @Column(nullable = false)
    private int balance;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TradeStatus status;
    @Column(nullable = false, length = 100)
    private String dealershipName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_code_id")
    private QrCode qrCode;

    @Builder
    private Trade(String summary, int withdrawalAmount, int depositAmount, String content, int balance, TradeStatus status, String dealershipName, Account account, QrCode qrCode) {
        this.summary = summary;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.content = content;
        this.balance = balance;
        this.status = status;
        this.dealershipName = dealershipName;
        this.account = account;
        this.qrCode = qrCode;
    }
}
