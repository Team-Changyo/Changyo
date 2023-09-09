package com.shinhan.changyo.api.controller.qrcode.response;

import com.shinhan.changyo.api.service.qrcode.dto.QrCodeResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QrCodeResponses {
    private int qrCodeSize;
    private List<QrCodeResponse> qrCodeResponses;

    @Builder
    public QrCodeResponses(int qrCodeSize, List<QrCodeResponse> qrCodeResponses) {
        this.qrCodeSize = qrCodeSize;
        this.qrCodeResponses = qrCodeResponses;
    }

    public static QrCodeResponses of(List<QrCodeResponse> qrCodes){
        return QrCodeResponses.builder()
                .qrCodeSize(qrCodes.size())
                .qrCodeResponses(qrCodes)
                .build();
    }
}
