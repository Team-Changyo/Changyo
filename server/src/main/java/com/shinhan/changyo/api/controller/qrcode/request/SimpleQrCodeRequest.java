package com.shinhan.changyo.api.controller.qrcode.request;

import com.shinhan.changyo.api.service.qrcode.dto.QrCodeDto;
import com.shinhan.changyo.api.service.qrcode.dto.SimpleQrCodeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleQrCodeRequest {
    /**
     * 계좌 식별 키
     * 금액
     * 링크
     */
    @NotBlank
    private String url;

    @NotBlank
    private Long accountId;

    @NotBlank
    private int amount;

    @Builder
    public SimpleQrCodeRequest(String url, Long accountId, int amount) {
        this.url = url;
        this.accountId = accountId;
        this.amount = amount;
    }

    public SimpleQrCodeDto toSimpleQrCodeDto(){
        return SimpleQrCodeDto.builder()
                .url(url)
                .accountId(accountId)
                .amount(amount)
                .build();
    }

}
