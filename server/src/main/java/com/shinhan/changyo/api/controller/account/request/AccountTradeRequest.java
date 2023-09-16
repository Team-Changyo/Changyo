package com.shinhan.changyo.api.controller.account.request;

import com.shinhan.changyo.api.service.account.dto.AccountTradeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AccountTradeRequest {

    @NotNull
    private Long accountId;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;

    @Builder
    public AccountTradeRequest(Long accountId, String startDate, String endDate) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AccountTradeDto toAccountTradeDto(String loginId) {
        return AccountTradeDto.builder()
                .accountId(this.accountId)
                .loginId(loginId)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
