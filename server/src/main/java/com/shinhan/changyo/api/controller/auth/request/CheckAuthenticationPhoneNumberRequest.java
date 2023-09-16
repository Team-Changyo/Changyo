package com.shinhan.changyo.api.controller.auth.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckAuthenticationPhoneNumberRequest {

    private String phoneNumber;
    private String authenticationNumber;

    @Builder
    private CheckAuthenticationPhoneNumberRequest(String phoneNumber, String authenticationNumber) {
        this.phoneNumber = phoneNumber;
        this.authenticationNumber = authenticationNumber;
    }
}
