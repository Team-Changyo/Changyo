package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

@Data
public class DepositOverviewResponse {
    private Long qrCodeId;
    private String qrCodeTitle;
    private int amount;
    private int remainTotal;
    private int remainCount;

    @Builder
    public DepositOverviewResponse(Long qrCodeId, String qrCodeTitle, int amount, int remainTotal, int remainCount) {
        this.qrCodeId = qrCodeId;
        this.qrCodeTitle = qrCodeTitle;
        this.amount = amount;
        this.remainTotal = remainTotal;
        this.remainCount = remainCount;
    }
}
