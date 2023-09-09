package com.shinhan.changyo.api.service.qrcode.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
public class EditAmountDto {

    private Long qrCodeId;

    private int amount;

    @Builder
    public EditAmountDto(Long qrCodeId, int amount) {
        this.qrCodeId = qrCodeId;
        this.amount = amount;
    }
}
