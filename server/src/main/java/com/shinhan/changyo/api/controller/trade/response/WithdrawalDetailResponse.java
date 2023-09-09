package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

@Data
public class WithdrawalDetailResponse {

    private Long tradeId;
    private String qrCodeTitle;
    private String depositName;
    private int amount;
    private String status;
    private String lastModifiedDate;

    @Builder
    public WithdrawalDetailResponse(Long tradeId, String qrCodeTitle, String depositName, int amount, String status, String lastModifiedDate) {
        this.tradeId = tradeId;
        this.qrCodeTitle = qrCodeTitle;
        this.depositName = depositName;
        this.amount = amount;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
    }
}
