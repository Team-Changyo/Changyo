package com.shinhan.api.api.controller.account.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerNameRequest {

    private String bankCode;
    private String accountNumber;

    @Builder
    public CustomerNameRequest(String bankCode, String accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }
}
