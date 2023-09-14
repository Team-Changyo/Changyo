package com.shinhan.changyo.api.service.qrcode.dto;

import com.shinhan.changyo.domain.qrcode.SimpleQrCode;
import lombok.Builder;
import lombok.Data;

@Data
public class SimpleQrCodeDto {
    private String accountNumber;

    private String bankCode;

    private int amount;

    @Builder
    public SimpleQrCodeDto(String accountNumber, String bankCode, int amount) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.amount = amount;
    }

    public SimpleQrCode toEntity(String memberName) {
        return SimpleQrCode.builder()
                .memberName(memberName)
                .accountNumber(this.accountNumber)
                .bankCode(this.bankCode)
                .amount(this.amount)
                .build();
    }
}
