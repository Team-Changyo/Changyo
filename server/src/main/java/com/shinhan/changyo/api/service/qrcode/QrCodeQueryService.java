package com.shinhan.changyo.api.service.qrcode;

import com.shinhan.changyo.api.controller.qrcode.response.QrCodeResponses;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeResponse;
import com.shinhan.changyo.domain.qrcode.repository.QrCodeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class QrCodeQueryService {
    private final QrCodeQueryRepository qrCodeQueryRepository;
    public QrCodeResponses getQrCodes(Long memberId) {
        List<QrCodeResponse> responses = qrCodeQueryRepository.getQrCodes(memberId);

        return QrCodeResponses.of(responses);
    }
}
