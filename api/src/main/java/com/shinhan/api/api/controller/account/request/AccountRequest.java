package com.shinhan.api.api.controller.account.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AccountRequest {

    @NotEmpty(message = "계좌 번호는 필수입니다.")
    private String accountNumber;

    @Builder
    private AccountRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
