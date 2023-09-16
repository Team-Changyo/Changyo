package com.shinhan.changyo.api.controller.auth.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckAuthenticationRequest {

    private String accountNumber;
    private String authenticationNumber;

    @Builder
    private CheckAuthenticationRequest(String accountNumber, String authenticationNumber) {
        this.accountNumber = accountNumber;
        this.authenticationNumber = authenticationNumber;
    }
}
