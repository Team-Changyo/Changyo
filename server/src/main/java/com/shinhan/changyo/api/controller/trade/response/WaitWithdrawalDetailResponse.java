package com.shinhan.changyo.api.controller.trade.response;

import com.shinhan.changyo.domain.trade.TradeStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class WaitWithdrawalDetailResponse {

    private Long tradeId;
    private String qrCodeTitle;
    private String memberName;
    private int amount;
    private String tradeDate;

    @Builder
    public WaitWithdrawalDetailResponse(Long tradeId, String qrCodeTitle, String memberName, int amount, TradeStatus status) {
        this.tradeId = tradeId;
        this.qrCodeTitle = qrCodeTitle;
        this.memberName = memberName;
        this.amount = amount;
        this.tradeDate = status.getText();
    }
}
