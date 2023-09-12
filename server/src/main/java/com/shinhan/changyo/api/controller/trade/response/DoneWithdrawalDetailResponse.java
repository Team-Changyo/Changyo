package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DoneWithdrawalDetailResponse {

    private Long tradeId;
    private String qrCodeTitle;
    private String memberName;
    private int amount;
    private String tradeDate;

    @Builder
    public DoneWithdrawalDetailResponse(Long tradeId, String qrCodeTitle, String memberName, int amount, LocalDateTime tradeDate) {
        this.tradeId = tradeId;
        this.qrCodeTitle = qrCodeTitle;
        this.memberName = memberName;
        this.amount = amount;
        this.tradeDate = tradeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}
