package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {
    private int totalAccounts;
    private List<AccountDetailResponse> accountDetailResponses;

    @Builder
    public AccountResponse(int totalAccounts, List<AccountDetailResponse> accountDetailResponses) {
        this.totalAccounts = totalAccounts;
        this.accountDetailResponses = accountDetailResponses;
    }
}
