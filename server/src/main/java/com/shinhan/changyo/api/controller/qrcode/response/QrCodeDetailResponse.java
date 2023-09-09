package com.shinhan.changyo.api.controller.qrcode.response;

import com.shinhan.changyo.domain.qrcode.QrCode;
import lombok.Builder;
import lombok.Data;

@Data
public class QrCodeDetailResponse {

    private String bankCode;

    private String accountNumber;

    private String title;

    private String customerName;

    private int amount;

    private String base64QrCode;

    private String url;

    @Builder
    public QrCodeDetailResponse(String bankCode, String accountNumber, String title, String customerName, int amount, String base64QrCode, String url) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.title = title;
        this.customerName = customerName;
        this.amount = amount;
        this.base64QrCode = base64QrCode;
        this.url = url;
    }

    public static QrCodeDetailResponse of(QrCode qrCode){
        return QrCodeDetailResponse.builder()
                .bankCode(qrCode.getAccount().getBankCode())
                .accountNumber(qrCode.getAccount().getAccountNumber())
                .title(qrCode.getTitle())
                .customerName(qrCode.getAccount().getCustomerName())
                .amount(qrCode.getAmount())
                .base64QrCode(qrCode.getBase64QrCode())
                .url(qrCode.getUrl())
                .build();
    }


}
