package com.shinhan.changyo.client.request;

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
    private TransferRequest(String withdrawalAccountNumber, String depositBankCode, String depositAccountNumber, int amount, String depositMemo, String withdrawalMemo) {
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.depositBankCode = depositBankCode;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.depositMemo = depositMemo;
        this.withdrawalMemo = withdrawalMemo;
    }
}