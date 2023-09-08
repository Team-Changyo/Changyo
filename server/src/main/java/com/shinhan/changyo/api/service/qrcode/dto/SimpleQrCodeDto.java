package com.shinhan.changyo.api.service.qrcode.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SimpleQrCodeDto {
    private String url;

    private Long accountId;

    private int amount;

    @Builder
    public SimpleQrCodeDto(String url, Long accountId, int amount) {
        this.url = url;
        this.accountId = accountId;
        this.amount = amount;
    }
}
