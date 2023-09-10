package com.shinhan.changyo.api.service.trade.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateTradeDto {
    private Long accountId;
    private Long qrCodeId;
    private int amount;
    private String content;

    @Builder
    public CreateTradeDto(Long accountId, Long qrCodeId, int amount, String content) {
        this.accountId = accountId;
        this.qrCodeId = qrCodeId;
        this.amount = amount;
        this.content = content;
    }
}
