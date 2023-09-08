package com.shinhan.changyo.api.controller.qrcode;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.qrcode.request.QrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeResponse;
import com.shinhan.changyo.api.service.qrcode.QrCodeQueryService;
import com.shinhan.changyo.api.service.qrcode.QrCodeService;
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
public class QrCodeController {

    private final QrCodeQueryService qrCodeQueryService;
    private final QrCodeService qrCodeService;
    /**
     * 보증금 QR코드 생성 요청
     */
    @PostMapping()
    public ApiResponse<QrCodeResponse> createQRCode(@RequestBody QrCodeRequest request){
        QrCodeResponse response = qrCodeService.createQRcode(request.toQrCodeDto());
        return ApiResponse.ok(response);
    }

    /**
     * 간편 송금 QR코드 생성 요청
     */

}
