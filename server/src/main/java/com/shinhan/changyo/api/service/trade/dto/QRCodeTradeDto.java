package com.shinhan.changyo.api.service.trade.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class QRCodeTradeDto {

    private String title;
    private int amount;

    @Builder
    public QRCodeTradeDto(String title, int amount) {
        this.title = title;
        this.amount = amount;
    }
}
