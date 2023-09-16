package com.shinhan.changyo.api.controller.trade.response;

import com.shinhan.changyo.api.service.trade.dto.QrCodeTradeDto;
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

    public static DepositOverviewResponse of(QrCodeTradeDto dto, int remainCount, int remainTotal) {
        return DepositOverviewResponse.builder()
                .qrCodeId(dto.getQrCodeId())
                .qrCodeTitle(dto.getTitle())
                .amount(dto.getAmount())
                .remainTotal(remainTotal)
                .remainCount(remainCount)
                .build();
    }
}
