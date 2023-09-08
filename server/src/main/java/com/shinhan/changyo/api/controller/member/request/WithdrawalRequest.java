package com.shinhan.changyo.api.controller.member.request;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class WithdrawalRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    @Builder
    public WithdrawalRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
