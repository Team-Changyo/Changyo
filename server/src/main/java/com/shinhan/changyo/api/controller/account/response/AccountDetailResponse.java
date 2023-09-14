package com.shinhan.changyo.api.controller.account.response;

import com.shinhan.changyo.domain.account.Account;
import lombok.Builder;
import lombok.Data;

@Data
public class AccountDetailResponse {
    private Long accountId;
    private String accountNumber;
    private int balance;
    private String bankCode;
    private Boolean mainAccount;
    private String title;

    @Builder
    public AccountDetailResponse(Long accountId, String accountNumber, int balance, String bankCode, Boolean mainAccount, String title) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        this.mainAccount = mainAccount;
        this.title = title;
    }
}
