package com.shinhan.api.domain.trade;

import com.shinhan.api.domain.TimeBaseEntity;
import com.shinhan.api.domain.account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trade extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long id;

    private LocalDateTime tradeDate;
    private LocalDateTime tradeTime;
    private String summary;

    private int withdrawalAmount;
    private int depositAmount;
    private String content;
    private int balance;
    private String status;
    private String dealershipName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    private Trade(LocalDateTime tradeDate, LocalDateTime tradeTime, String summary, int withdrawalAmount, int depositAmount, String content, int balance, String status, String dealershipName) {
        this.tradeDate = tradeDate;
        this.tradeTime = tradeTime;
        this.summary = summary;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.content = content;
        this.balance = balance;
        this.status = status;
        this.dealershipName = dealershipName;
    }
}
