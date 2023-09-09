package com.shinhan.changyo.api.controller.qrcode.response;

import com.shinhan.changyo.api.service.qrcode.dto.QrCodeResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QrCodeResponses {
    private int qrCodesize;
    private List<QrCodeResponse> qrCodeResponses;

    @Builder
    public QrCodeResponses(int qrCodesize, List<QrCodeResponse> qrCodeResponses) {
        this.qrCodesize = qrCodesize;
        this.qrCodeResponses = qrCodeResponses;
    }

    public static QrCodeResponses of(List<QrCodeResponse> qrCodes){
        return QrCodeResponses.builder()
                .qrCodesize(qrCodes.size())
                .qrCodeResponses(qrCodes)
                .build();
    }
}
