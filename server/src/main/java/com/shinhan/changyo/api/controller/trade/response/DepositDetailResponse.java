package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DepositDetailResponse {
    private String qrCodeTitle;
    private int amount;
    private int remainTotal;
    private int remainCount;
    List<DepositDetail> depositDetails;

    @Builder
    public DepositDetailResponse(String qrCodeTitle, int amount, int remainTotal, int remainCount, List<DepositDetail> depositDetails) {
        this.qrCodeTitle = qrCodeTitle;
        this.amount = amount;
        this.remainTotal = remainTotal;
        this.remainCount = remainCount;
        this.depositDetails = depositDetails;
    }
}
