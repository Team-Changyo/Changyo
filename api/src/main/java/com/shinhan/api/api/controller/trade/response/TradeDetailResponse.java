package com.shinhan.api.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TradeDetailResponse {

    private String tradeDate;
    private String tradeTime;
    private String summary;
    private int withdrawalAmount;
    private int depositAmount;
    private String content;
    private int balance;
    private int status;
    private String dealershipName;

    @Builder
    public TradeDetailResponse(LocalDateTime tradeDateTime, String summary, int withdrawalAmount, int depositAmount, String content, int balance, int status, String dealershipName) {
        this.tradeDate = tradeDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.tradeTime = tradeDateTime.format(DateTimeFormatter.ofPattern("hhmmss"));
        this.summary = summary;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.content = content;
        this.balance = balance;
        this.status = status;
        this.dealershipName = dealershipName;
    }
}