package com.shinhan.changyo.api.controller.qrcode.request;

import com.shinhan.changyo.api.service.qrcode.dto.SimpleQrCodeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SimpleQrCodeRequest {

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String bankCode;

    @NotNull
    private int amount;

    @Builder
    public SimpleQrCodeRequest(String accountNumber, String bankCode, int amount) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.amount = amount;
    }

    public SimpleQrCodeDto toSimpleQrCodeDto() {
        return SimpleQrCodeDto.builder()
                .accountNumber(this.accountNumber)
                .bankCode(this.bankCode)
                .amount(this.amount)
                .build();
    }
}
