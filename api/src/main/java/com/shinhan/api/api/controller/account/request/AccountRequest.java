package com.shinhan.api.api.controller.account.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRequest {

    private String accountNumber;

    @Builder
    public AccountRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
