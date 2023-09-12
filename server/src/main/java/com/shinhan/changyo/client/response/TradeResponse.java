package com.shinhan.changyo.client.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TradeResponse {
    private String accountNumber;
    private String productName;
    private int balance;
    private String customerName;
    private int tradeSize;
    private List<TradeDetailResponse> trades;

    @Builder
    public TradeResponse(String accountNumber, String productName, int balance, String customerName, int tradeSize, List<TradeDetailResponse> trades) {
        this.accountNumber = accountNumber;
        this.productName = productName;
        this.balance = balance;
        this.customerName = customerName;
        this.tradeSize = tradeSize;
        this.trades = trades;
    }

}
