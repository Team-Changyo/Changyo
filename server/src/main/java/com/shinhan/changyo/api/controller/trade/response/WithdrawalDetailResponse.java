package com.shinhan.changyo.api.controller.trade.response;

import com.shinhan.changyo.domain.trade.TradeStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class WithdrawalDetailResponse {

    private Long tradeId;
    private String qrCodeTitle;
    private String memberName;
    private int withdrawalAmount;
    private TradeStatus status;
    private String tradeDate;

    @Builder
    public WithdrawalDetailResponse(Long tradeId, String qrCodeTitle, String memberName, int withdrawalAmount, TradeStatus status, LocalDateTime tradeDate) {
        this.tradeId = tradeId;
        this.qrCodeTitle = qrCodeTitle;
        this.memberName = memberName;
        this.withdrawalAmount = withdrawalAmount;
        this.status = status;
        this.tradeDate = tradeDate.format(DateTimeFormatter.ofPattern("yyyyMMdd hhmm"));
    }
}
