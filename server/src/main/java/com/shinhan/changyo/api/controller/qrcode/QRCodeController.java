package com.shinhan.changyo.api.controller.qrcode;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.qrcode.request.QRCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QRCodeResponse;
import com.shinhan.changyo.api.service.qrcode.QRCodeQueryService;
import com.shinhan.changyo.api.service.qrcode.QRCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/qrcode-management/qrcode")
public class QRCodeController {

    private final QRCodeQueryService qrCodeQueryService;
    private final QRCodeService qrCodeService;
    /**
     * 보증금 QR코드 생성 요청
     */
    @PostMapping()
    public ApiResponse<QRCodeResponse> createQRCode(){ //@RequestBody QRCodeRequest qrCodeRequest
        QRCodeResponse response = qrCodeService.createQRcode(null);
        return ApiResponse.ok(response);
    }

    /**
     * 간편 송금 QR코드 생성 요청
     */

}
