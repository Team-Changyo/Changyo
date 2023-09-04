package com.shinhan.api.api.controller.account.response;

import lombok.Data;

@Data
public class CustomerNameResponse {

    private String bankCode;
    private String accountNumber;
    private String customerName;

    public CustomerNameResponse(String bankCode, String accountNumber, String customerName) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
    }
}
