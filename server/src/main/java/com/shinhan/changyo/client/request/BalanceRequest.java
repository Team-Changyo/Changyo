package com.shinhan.changyo.client.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BalanceRequest {

    private String accountNumber;

    @Builder
    public BalanceRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
