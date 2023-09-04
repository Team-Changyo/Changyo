package com.shinhan.api.api.controller.account.response;

import lombok.Data;

@Data
public class AccountResponse {

    private String accountNumber;
    private int balance;

    public AccountResponse(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
