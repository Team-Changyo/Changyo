package com.shinhan.changyo.client.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BalanceResponse {

    private String accountNumber;
    private int balance;

    @Builder
    public BalanceResponse(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        }
    }
