package com.shinhan.changyo.api.controller.qrcode.response;

import com.shinhan.changyo.domain.qrcode.SimpleQrCode;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class SimpleQrCodeResponse {
    private Long simpleQrCodeId;
    private String url;
    private String base64QrCode;
    private String memberName;
    private String accountNumber;
    private String bankCode;
    private int amount;
    private String createdDate;

    @Builder
    public SimpleQrCodeResponse(Long simpleQrCodeId, String url, String base64QrCode, String memberName, String accountNumber, String bankCode, int amount, String createdDate) {
        this.simpleQrCodeId = simpleQrCodeId;
        this.url = url;
        this.base64QrCode = base64QrCode;
        this.memberName = memberName;
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.amount = amount;
        this.createdDate = createdDate;
    }

    public static SimpleQrCodeResponse of(SimpleQrCode saveQrCode) {
        return SimpleQrCodeResponse.builder()
                .simpleQrCodeId(saveQrCode.getId())
                .url(saveQrCode.getUrl())
                .base64QrCode(saveQrCode.getBase64QrCode())
                .memberName(saveQrCode.getMemberName())
                .accountNumber(saveQrCode.getAccountNumber())
                .bankCode(saveQrCode.getBankCode())
                .amount(saveQrCode.getAmount())
                .createdDate(saveQrCode.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")))
                .build();

    }
}