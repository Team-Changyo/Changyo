package com.shinhan.changyo.api.controller.account.response;

import com.shinhan.changyo.domain.account.Account;
import lombok.Builder;
import lombok.Data;

@Data
public class AccountEditResponse {
    private Long accountId;
    private String accountNumber;
    private int balance;
    private String bankCode;
    private String title;
    private Boolean mainAccount;

    @Builder
    public AccountEditResponse(Long accountId, String accountNumber, int balance, String bankCode, String title, Boolean mainAccount) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        this.title = title;
        this.mainAccount = mainAccount;
    }

    public static AccountEditResponse of(Account account) {
        return AccountEditResponse.builder()
                .accountId(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .bankCode(account.getBankCode())
                .title(account.getTitle())
                .mainAccount(account.getMainAccount())
                .build();
    }
}
