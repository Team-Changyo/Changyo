package com.shinhan.changyo.api.controller.auth.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationPhoneNumberRequest {

    private String phoneNumber;

    @Builder
    private AuthenticationPhoneNumberRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
