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
    private Account(String accountNumber, int balance, String productName, String customerName, String bankCode) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productName = productName;
        this.customerName = customerName;
        this.bankCode = bankCode;
    }

    //== 비즈니스 로직 ==//
    public int withdrawal(int amount) {
        int result = this.balance - amount;
        if (result < 0) {
            throw new IllegalArgumentException("계좌 잔액이 부족합니다.");
        }
        this.balance = result;
        return result;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }
}
