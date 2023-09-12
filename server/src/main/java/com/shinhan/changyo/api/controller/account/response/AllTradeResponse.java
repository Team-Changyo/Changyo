package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AllTradeResponse {
    private String tradeTime;
    private String content;
    private int balanc;
    private int withdrawalAmount;
    private int depositAmount;

    @Builder
    public AllTradeResponse(String tradeTime, String content, int balanc, int withdrawalAmount, int depositAmount) {
        this.tradeTime = tradeTime;
        this.content = content;
        this.balanc = balanc;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
    }
}
