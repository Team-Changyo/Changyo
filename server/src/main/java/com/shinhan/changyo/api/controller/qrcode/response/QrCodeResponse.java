package com.shinhan.changyo.api.controller.qrcode.response;

import lombok.Builder;
import lombok.Data;

@Data
public class QrCodeResponse {

    String qrCoded;

    @Builder
    public QrCodeResponse(String qrCoded) {
        this.qrCoded = qrCoded;
    }
}
