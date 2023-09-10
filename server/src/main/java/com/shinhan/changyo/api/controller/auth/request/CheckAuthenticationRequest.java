package com.shinhan.changyo.api.controller.auth.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckAuthenticationRequest {

    private String authenticationNumber;

    @Builder
    private CheckAuthenticationRequest(String authenticationNumber) {
        this.authenticationNumber = authenticationNumber;
    }
}
