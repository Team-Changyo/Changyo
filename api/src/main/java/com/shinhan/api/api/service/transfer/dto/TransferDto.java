package com.shinhan.api.api.service.transfer.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TransferDto {

    private String withdrawalAccountNumber;
    private String depositBankCode;
    private String depositAccountNumber;
    private int amount;
    private String depositMemo;
    private String withdrawalMemo;

    @Builder
    public TransferDto(String withdrawalAccountNumber, String depositBankCode, String depositAccountNumber, int amount, String depositMemo, String withdrawalMemo) {
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.depositBankCode = depositBankCode;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.depositMemo = depositMemo;
        this.withdrawalMemo = withdrawalMemo;
    }
}
