package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

@Data
public class WithdrawalDetailResponse {

    private Long tradeId;
    private String qrCodeTitle;
    private String depositName;
    private int amount;
    private String returnDate;

    @Builder
    public WithdrawalDetailResponse(Long tradeId, String qrCodeTitle, String depositName, int amount, String returnDate) {
        this.tradeId = tradeId;
        this.qrCodeTitle = qrCodeTitle;
        this.depositName = depositName;
        this.amount = amount;
        this.returnDate = returnDate;
    }
}
