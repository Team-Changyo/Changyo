package com.shinhan.changyo.api.service.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BalanceResponse {

    private String accountNumber;
    private int balance;

    @Builder
    public BalanceResponse(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
