package com.shinhan.changyo.api.controller.qrcode.request;

import com.shinhan.changyo.api.service.qrcode.dto.QrCodeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class QrCodeRequest {
    /**
     * 계좌 식별 키
     * 금액
     * 링크
     * 표시명
     */

    @NotBlank
    private Long accountId;

    @NotBlank
    private int amount;

    @NotBlank
    private String title;

    @Builder
    public QrCodeRequest(Long accountId, int amount, String title) {
        this.accountId = accountId;
        this.amount = amount;
        this.title = title;
    }

    public QrCodeDto toQrCodeDto(String loginId){
        return QrCodeDto.builder()
                .accountId(accountId)
                .amount(amount)
                .title(title)
                .loginId(loginId)
                .build();
    }
}
