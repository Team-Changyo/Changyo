package com.shinhan.changyo.api.controller.account.response;

import com.shinhan.changyo.domain.account.Account;
import lombok.Builder;
import lombok.Data;

/**
 * Acoount 수정 시 response
 */
@Data
public class AccountEditResponse {
    private String accountNumber;
    private int balance;
    private String bankCode;
    private String title;
    private Boolean mainAccount;

    @Builder
    public AccountEditResponse(String accountNumber, int balance, String bankCode, String title, Boolean mainAccount) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        this.title = title;
        this.mainAccount = mainAccount;
    }

    public static AccountEditResponse of(Account account){
        return AccountEditResponse.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .bankCode(account.getBankCode())
                .title(account.getTitle())
                .mainAccount(account.getMainAccount())
                .build();
    }
}
