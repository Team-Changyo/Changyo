package com.shinhan.changyo.api.controller.transfer.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SimpleStoreAccountResponse {
    private Long simpleQrCodeId;
    private String memberName;
    private int amount;
    private String bankCode;
    private String accountNumber;

    @Builder
    public SimpleStoreAccountResponse(Long simpleQrCodeId, String memberName, int amount, String bankCode, String accountNumber) {
        this.simpleQrCodeId = simpleQrCodeId;
        this.memberName = memberName;
        this.amount = amount;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }
}
