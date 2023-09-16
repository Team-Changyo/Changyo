package com.shinhan.changyo.client.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OneTransferResponse {

    private String bankCode;
    private String accountNumber;

    @Builder
    public OneTransferResponse(String bankCode, String accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }
}
