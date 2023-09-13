package com.shinhan.changyo.client.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AccountDetailRequest {

    private String accountNumber;

    @Builder
    private AccountDetailRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
