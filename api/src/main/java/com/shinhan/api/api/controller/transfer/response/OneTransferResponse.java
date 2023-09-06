package com.shinhan.api.api.controller.transfer.response;

import lombok.Builder;
import lombok.Data;

@Data
public class OneTransferResponse {

    private String bankCode;
    private String accountNumber;

    @Builder
    public OneTransferResponse(String bankCode, String accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }
}
