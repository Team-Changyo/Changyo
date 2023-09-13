package com.shinhan.changyo.api.controller.transfer.response;

import lombok.Builder;
import lombok.Data;

@Data
public class StoreAccountResponse {
    private Long qrCodeId;
    private String qrCodeTitle;
    private int amount;
    private String bankCode;
    private String accountNumber;
    private String productName;
    private String memberName;

    @Builder
    public StoreAccountResponse(Long qrCodeId, String qrCodeTitle, int amount, String bankCode, String accountNumber, String productName, String memberName) {
        this.qrCodeId = qrCodeId;
        this.qrCodeTitle = qrCodeTitle;
        this.amount = amount;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.productName = "입출금통장";
        this.memberName = memberName;
    }
}
