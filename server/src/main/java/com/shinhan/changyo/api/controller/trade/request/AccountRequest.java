package com.shinhan.changyo.api.controller.trade.request;

import com.shinhan.changyo.api.service.account.dto.AccountDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRequest {
    private Long accountId;

    @Builder
    public AccountRequest(Long accountId) {
        this.accountId = accountId;
    }

    public AccountDto toAccountDto(String loginId) {
        return AccountDto.builder()
                .accountId(accountId)
                .loginId(loginId)
                .build();
    }
}
