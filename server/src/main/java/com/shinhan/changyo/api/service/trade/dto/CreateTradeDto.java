package com.shinhan.changyo.api.service.trade.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateTradeDto {
    private Long accountId;
    private String withdrawalAccountNumber;
    private Long qrCodeId;
    private String depositAccountNumber;
    private int amount;
    private String content;

    @Builder
    public CreateTradeDto(Long accountId, String withdrawalAccountNumber, Long qrCodeId, String depositAccountNumber, int amount, String content) {
        this.accountId = accountId;
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.qrCodeId = qrCodeId;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.content = content;
    }
}
