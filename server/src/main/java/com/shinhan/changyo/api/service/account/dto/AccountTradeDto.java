package com.shinhan.changyo.api.service.account.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Data
public class AccountTradeDto {
    private Long accountId;
    private String loginId;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public AccountTradeDto(Long accountId, String loginId, String startDate, String endDate) {
        this.accountId = accountId;
        this.loginId = loginId;
        this.startDate = StringUtils.hasText(startDate) ? LocalDate.parse(startDate).minusDays(1) : LocalDate.MIN;
        this.endDate = StringUtils.hasText(endDate) ? LocalDate.parse(endDate).plusDays(1) : LocalDate.MAX;
    }
}
