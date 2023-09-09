package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AccountDetailResponse {

    private String accountNumber;
    private int balance;
    private String bankCode;
    private Boolean mainAccount;

    @Builder
    public AccountDetailResponse(String accountNumber, int balance, String bankCode, Boolean mainAccount) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        this.mainAccount = mainAccount;
    }

}
