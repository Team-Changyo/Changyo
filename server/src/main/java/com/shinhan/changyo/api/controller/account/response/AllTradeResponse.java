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
    private int status; // 1 입금 2 출금

    @Builder
    public AllTradeResponse(String tradeDate, String tradeTime, String content, int balance, int withdrawalAmount, int depositAmount, int status) {
        this.tradeDate = String.format("%s-%s-%s", tradeDate.substring(0, 4), tradeDate.substring(4, 6), tradeDate.substring(6, 8));
        this.tradeTime = String.format("%s:%s", tradeTime.substring(0, 2), tradeTime.substring(2, 4));
        this.content = content;
        this.balance = balance;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.status = status;
    }
}
