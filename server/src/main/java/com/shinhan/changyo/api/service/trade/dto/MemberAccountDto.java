package com.shinhan.changyo.api.service.trade.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberAccountDto {

    private Long accountId;
    private String accountNumber;
    private String memberName;

    @Builder
    public MemberAccountDto(Long accountId, String accountNumber, String memberName) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.memberName = memberName;
    }
}
