package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AccountTradeAllResponse {
    private Long accountId;
    private String accountNumber;
    private int balance;
    private String bankCode;
    private List<AllTradeResponses> allTradeResponses;

    @Builder
    public AccountTradeAllResponse(Long accountId, String accountNumber, int balance, String bankCode, List<AllTradeResponses> allTradeResponses) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        this.allTradeResponses = allTradeResponses;
    }
}
