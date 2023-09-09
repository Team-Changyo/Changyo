package com.shinhan.changyo.api.service.qrcode.dto;

import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.qrcode.QrCode;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QrCodeDto {

    private String url;

    private Long accountId;

    private int amount;

    private String title;

    @Builder
    public QrCodeDto(String url, Long accountId, int amount, String title) {
        this.url = url;
        this.accountId = accountId;
        this.amount = amount;
        this.title = title;
    }

    public QrCode toEntity(String storeFileName, Account account){
        return QrCode.builder()
                .url(this.url)
                .amount(this.amount)
                .title(this.title)
                .storeFileName(storeFileName)
                .active(true)
                .account(account)
                .build();

    }
}
