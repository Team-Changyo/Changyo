package com.shinhan.api.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AccountResponse {

    private String accountNumber;
    private int balance;

    @Builder
    public AccountResponse(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
