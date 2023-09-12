package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AccountTradeAllResponse {
    private Long accountId;
    private String accountNumber;
    private int balance;
    private String bankCode;
    private Map<String, List<AllTradeResponse>> allTradeResponses;
//    private List<AllTradeResponses> allTradeResponses;

    @Builder
    public AccountTradeAllResponse(Long accountId, String accountNumber, int balance, String bankCode, Map<String, List<AllTradeResponse>> allTradeResponses) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankCode = bankCode;
        this.allTradeResponses = allTradeResponses;
    }
}
