package com.shinhan.changyo.api.controller.account.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AllTradeResponses {
    int tradeDate;
    List<AllTradeResponse> allTradeResponses;

    @Builder
    public AllTradeResponses(int tradeDate, List<AllTradeResponse> allTradeResponses) {
        this.tradeDate = tradeDate;
        this.allTradeResponses = allTradeResponses;
    }
}
