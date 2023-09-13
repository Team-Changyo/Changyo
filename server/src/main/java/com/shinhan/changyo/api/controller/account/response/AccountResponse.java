package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {
    private int accountSize;
    private int accountTotalBalance;
    private List<String> bankCodeList;
    private List<AccountDetailResponse> accountDetailResponses;

    @Builder
    public AccountResponse(int accountSize, int accountTotalBalance, List<String> bankCodeList, List<AccountDetailResponse> accountDetailResponses) {
        this.accountSize = accountSize;
        this.accountTotalBalance = accountTotalBalance;
        this.bankCodeList = bankCodeList;
        this.accountDetailResponses = accountDetailResponses;
    }

    public static AccountResponse of(List<AccountDetailResponse> accounts, Integer accountTotalBalance, List<String> bankCodeList, Integer accountSize) {
        return AccountResponse.builder()
                .accountSize(accountSize)
                .accountTotalBalance(accountTotalBalance)
                .bankCodeList(bankCodeList)
                .accountDetailResponses(accounts)
                .build();
    }
}
