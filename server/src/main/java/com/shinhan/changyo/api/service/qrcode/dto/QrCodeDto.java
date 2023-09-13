package com.shinhan.changyo.api.service.qrcode.dto;

import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.qrcode.QrCode;
import lombok.Builder;
import lombok.Data;

@Data
public class QrCodeDto {

    private Long accountId;

    private int amount;

    private String title;

    private String loginId;
    @Builder
    public QrCodeDto(Long accountId, int amount, String title, String loginId) {
        this.accountId = accountId;
        this.amount = amount;
        this.title = title;
        this.loginId = loginId;
    }

    public QrCode toEntity(String url, String base64QrCode, Account account){
        return QrCode.builder()
                .url(url)
                .amount(this.amount)
                .title(this.title)
                .base64QrCode(base64QrCode)
                .active(true)
                .account(account)
                .build();

    }
}
