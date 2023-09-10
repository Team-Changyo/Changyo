package com.shinhan.changyo.api.service.trade.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ReturnDepositDto {
    private Long tradeId;
    private int amount;
    private int fee;
    private String reason;
    private String description;

    @Builder
    public ReturnDepositDto(Long tradeId, int amount, int fee, String reason, String description) {
        this.tradeId = tradeId;
        this.amount = amount;
        this.fee = fee;
        this.reason = reason;
        this.description = description;
    }
}
