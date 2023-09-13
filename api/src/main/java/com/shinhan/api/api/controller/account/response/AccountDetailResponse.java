package com.shinhan.api.api.controller.account.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDetailResponse {
    private int balance;
    private String productName;
    private String customerName;

    public AccountDetailResponse(int balance, String productName, String customerName) {
        this.balance = balance;
        this.productName = productName;
        this.customerName = customerName;
    }
}
