package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {
    private int accountSize;
    private List<AccountDetailResponse> accountDetailResponses;

    @Builder
    public AccountResponse(int accountSize, List<AccountDetailResponse> accountDetailResponses) {
        this.accountSize = accountSize;
        this.accountDetailResponses = accountDetailResponses;
    }

    public static AccountResponse of(List<AccountDetailResponse> accounts, Integer accountSize) {
        return AccountResponse.builder()
                .accountSize(accountSize)
                .accountDetailResponses(accounts)
                .build();
    }
}
