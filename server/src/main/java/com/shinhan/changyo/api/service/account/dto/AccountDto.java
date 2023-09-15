package com.shinhan.changyo.api.service.account.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {

    private Long accountId;
    private String loginId;

    @Builder
    public AccountDto(Long accountId, String loginId) {
        this.accountId = accountId;
        this.loginId = loginId;
    }
}
