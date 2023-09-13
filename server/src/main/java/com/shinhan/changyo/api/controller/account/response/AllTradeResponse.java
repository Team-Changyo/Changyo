package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AllTradeResponse {
    private String tradeDate;
    private String tradeTime;
    private String content;
    private int balance;
    private int withdrawalAmount;
    private int depositAmount;

    @Builder
    public AllTradeResponse(String tradeDate, String tradeTime, String content, int balance, int withdrawalAmount, int depositAmount) {
        this.tradeDate = tradeDate;
        this.tradeTime = tradeTime;
        this.content = content;
        this.balance = balance;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
    }
}
