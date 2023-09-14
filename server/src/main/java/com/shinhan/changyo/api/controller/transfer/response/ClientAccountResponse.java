package com.shinhan.changyo.api.controller.transfer.response;

import lombok.Builder;
import lombok.Data;

@Data
public class ClientAccountResponse {
    private Long accountId;
    private String bankCode;
    private String accountNumber;
    private String productName;
    private String memberName;
    private int balance;

    @Builder
    public ClientAccountResponse(Long accountId, String bankCode, String accountNumber, String productName, String memberName, int balance) {
        this.accountId = accountId;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.productName = "입출금통장";
        this.memberName = memberName;
        this.balance = balance;
    }
}
