package com.shinhan.changyo.api.controller.qrcode.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SimpleQrCodeResponse {
    /**
     * 은행 코드
     * 계좌번호
     * 계좌명
     * 금액
     * qr코드
     * url
     * 생성시간(?)-> 고민 필요
     */
    private String bankCode;

    private String accountNumber;

    private String customerName; // TODO: 2023-09-09 홍진식 : 고객 실명 조회 api 사용 ?

    private int amount;

    private String base64QrCode;

    private String url;

    @Builder
    public SimpleQrCodeResponse(String bankCode, String accountNumber, String customerName, int amount, String base64QrCode, String url) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.amount = amount;
        this.base64QrCode = base64QrCode;
        this.url = url;
    }
}
