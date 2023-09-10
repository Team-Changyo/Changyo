package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

@Data
public class DepositDetail {
    private Long tradeId;
    private String withdrawalName;
    private String tradeDate;

    @Builder
    public DepositDetail(Long tradeId, String withdrawalName, String tradeDate) {
        this.tradeId = tradeId;
        this.withdrawalName = withdrawalName;
        this.tradeDate = tradeDate;
    }
}
