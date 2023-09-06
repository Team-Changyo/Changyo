package com.shinhan.api.api.service.transfer.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class OneTransferDto {

    private String bankCode;
    private String accountNumber;
    private String memo;

    @Builder
    public OneTransferDto(String bankCode, String accountNumber, String memo) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.memo = memo;
    }
}
