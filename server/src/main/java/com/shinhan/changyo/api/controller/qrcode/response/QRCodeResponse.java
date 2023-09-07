package com.shinhan.changyo.api.controller.qrcode.response;

import lombok.Builder;
import lombok.Data;

@Data
public class QRCodeResponse {

    String qrCoded;

    @Builder
    public QRCodeResponse(String qrCoded) {
        this.qrCoded = qrCoded;
    }
}
