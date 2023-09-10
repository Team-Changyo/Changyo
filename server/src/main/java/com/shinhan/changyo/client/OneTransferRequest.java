package com.shinhan.changyo.client;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OneTransferRequest {

    private String bankCode;
    private String accountNumber;
    private String memo;

    @Builder
    public OneTransferRequest(String bankCode, String accountNumber, String memo) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.memo = memo;
    }
}
