package com.shinhan.api.api.controller.trade.response;

import lombok.Data;

@Data
public class TradeDetailResponse {

    private String tradeDate;
    private String tradeTime;
    private String summary;
    private int withdrawalAmount;
    private int depositAmount;
    private String content;
    private int balance;
    private String status;
    private String dealershipName;

    public TradeDetailResponse(String tradeDate, String tradeTime, String summary, int withdrawalAmount, int depositAmount, String content, int balance, String status, String dealershipName) {
        this.tradeDate = tradeDate;
        this.tradeTime = tradeTime;
        this.summary = summary;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.content = content;
        this.balance = balance;
        this.status = status;
        this.dealershipName = dealershipName;
    }
}