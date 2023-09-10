package com.shinhan.changyo.api.controller.auth.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank
    private String bankCode;

    @NotBlank
    private String accountNumber;

    @Builder
    private AuthenticationRequest(String bankCode, String accountNumber) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }
}
