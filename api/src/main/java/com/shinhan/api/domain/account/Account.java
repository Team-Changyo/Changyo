package com.shinhan.api.domain.account;

import com.shinhan.api.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    private String accountNumber;
    private int balance;
    private String productName;
    private String customerName;
    private String bankCode;

    @Builder
    public Account(String accountNumber, int balance, String productName, String customerName, String bankCode) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productName = productName;
        this.customerName = customerName;
        this.bankCode = bankCode;
    }
}
