package com.shinhan.changyo.api.service.qrcode.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class QrCodeResponse {
    /**
     * 제목
     * 계좌번호
     * 금액
     */
    private Long qrCodeId;
    private String title;
    private String accountNumber;
    private String bankCode;
    private int amount;

    @Builder
    public QrCodeResponse(Long qrCodeId, String title, String accountNumber, String bankCode, int amount) {
        this.qrCodeId = qrCodeId;
        this.title = title;
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.amount = amount;
    }
}
