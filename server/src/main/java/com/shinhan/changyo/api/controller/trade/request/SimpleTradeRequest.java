package com.shinhan.changyo.api.controller.trade.request;

import com.shinhan.changyo.api.service.trade.dto.SimpleTradeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleTradeRequest {
    private Long accountId;
    private String withdrawalAccountNumber;
    private String bankCode;
    private String depositAccountNumber;
    private int amount;
    private String qrCodeTitle;

    @Builder
    public SimpleTradeRequest(Long accountId, String withdrawalAccountNumber, String bankCode, String depositAccountNumber, int amount, String qrCodeTitle) {
        this.accountId = accountId;
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.bankCode = bankCode;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.qrCodeTitle = qrCodeTitle;
    }

    public SimpleTradeDto toSimpleTransferDto() {
        return SimpleTradeDto.builder()
                .accountId(this.accountId)
                .withdrawalAccountNumber(this.withdrawalAccountNumber)
                .bankCode(this.bankCode)
                .depositAccountNumber(this.depositAccountNumber)
                .amount(this.amount)
                .qrCodeTitle(this.qrCodeTitle)
                .build();
    }
}
