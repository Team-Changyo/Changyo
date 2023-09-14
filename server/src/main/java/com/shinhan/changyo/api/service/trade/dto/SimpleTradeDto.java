package com.shinhan.changyo.api.service.trade.dto;

import com.shinhan.changyo.client.request.TransferRequest;
import lombok.Builder;
import lombok.Data;

@Data
public class SimpleTradeDto {

    private Long accountId;
    private String withdrawalAccountNumber;
    private String bankCode;
    private String depositAccountNumber;
    private int amount;
    private String qrCodeTitle;

    @Builder
    public SimpleTradeDto(Long accountId, String withdrawalAccountNumber, String bankCode, String depositAccountNumber, int amount, String qrCodeTitle) {
        this.accountId = accountId;
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.bankCode = bankCode;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.qrCodeTitle = qrCodeTitle;
    }

    public TransferRequest toTransferRequest(String memberName) {
        return TransferRequest.builder()
                .withdrawalAccountNumber(this.withdrawalAccountNumber)
                .depositBankCode(this.bankCode)
                .depositAccountNumber(this.depositAccountNumber)
                .amount(this.amount)
                .depositMemo(memberName)
                .withdrawalMemo(this.qrCodeTitle)
                .build();
    }
}
