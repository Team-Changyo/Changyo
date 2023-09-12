package com.shinhan.changyo.client.response;

import lombok.Builder;
import lombok.Data;

@Data
public class TransferResponse {

    private String withdrawalAccountNumber;
    private String depositBankCode;
    private String depositAccountNumber;
    private int amount;
    private String depositMemo;
    private String withdrawalMemo;
    private int result;

    @Builder
    public TransferResponse(String withdrawalAccountNumber, String depositBankCode, String depositAccountNumber, int amount, String depositMemo, String withdrawalMemo, int result) {
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.depositBankCode = depositBankCode;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.depositMemo = depositMemo;
        this.withdrawalMemo = withdrawalMemo;
        this.result = result;
    }
}
