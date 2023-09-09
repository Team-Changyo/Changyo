package com.shinhan.api.api.controller.transfer.response;

import com.shinhan.api.domain.account.Account;
import lombok.Builder;
import lombok.Data;

@Data
public class OneTransferResponse {

    private String bankCode;
    private String accountNumber;

    @Builder
    public OneTransferResponse(String bankCode, String accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }

    public static OneTransferResponse of(Account account) {
        return OneTransferResponse.builder()
            .bankCode(account.getBankCode())
            .accountNumber(account.getAccountNumber())
            .build();
    }
}
