package com.shinhan.api.api.controller.trade.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeRequest {

    private String accountNumber;

    @Builder
    public TradeRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
