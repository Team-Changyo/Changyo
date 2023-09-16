package com.shinhan.changyo.api.service.trade.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class QrCodeTradeDto {

    private Long qrCodeId;
    private String title;
    private int amount;

    @Builder
    public QrCodeTradeDto(Long qrCodeId, String title, int amount) {
        this.qrCodeId = qrCodeId;
        this.title = title;
        this.amount = amount;
    }
}
