package com.shinhan.api.api.controller.trade.response;

import com.shinhan.api.domain.account.Account;
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

    public static TradeResponse of(Account account, List<TradeDetailResponse> responses) {
        return TradeResponse.builder()
                .accountNumber(account.getAccountNumber())
                .productName(account.getProductName())
                .balance(account.getBalance())
                .customerName(account.getCustomerName())
                .tradeSize(responses.size())
                .trades(responses)
                .build();
    }
}
