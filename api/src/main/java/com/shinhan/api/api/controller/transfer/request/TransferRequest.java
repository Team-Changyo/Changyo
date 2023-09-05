package com.shinhan.api.api.controller.transfer.request;

import com.shinhan.api.api.service.transfer.dto.TransferDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferRequest {

    private String withdrawalAccountNumber;
    private String depositBankCode;
    private String depositAccountNumber;
    private int amount;
    private String depositMemo;
    private String withdrawalMemo;

    @Builder
    public TransferRequest(String withdrawalAccountNumber, String depositBankCode, String depositAccountNumber, int amount, String depositMemo, String withdrawalMemo) {
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.depositBankCode = depositBankCode;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.depositMemo = depositMemo;
        this.withdrawalMemo = withdrawalMemo;
    }

    public TransferDto toTransferDto() {
        return TransferDto.builder()
            .withdrawalAccountNumber(this.withdrawalAccountNumber)
            .depositBankCode(this.depositBankCode)
            .depositAccountNumber(this.depositAccountNumber)
            .amount(this.amount)
            .depositMemo(this.depositMemo)
            .withdrawalMemo(this.withdrawalMemo)
            .build();
    }
}
