package com.shinhan.changyo.api.controller.qrcode;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.qrcode.request.QrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.request.SimpleQrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeResponse;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
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
     *
     * @param request 등록할 보증금 QR코드 정보
     * @return 등록된 QR코드 식별키
     */
    @PostMapping()
    public ApiResponse<Long> createQrCode(@RequestBody QrCodeRequest request){
        log.debug("QrCodeRequest={}", request);
        Long saveId = qrCodeService.createQrcode(request.toQrCodeDto());
        return ApiResponse.ok(saveId);
    }

    /**
     * 단순 송금 QR코드 생성 요청
     *
     * @param request 생성할 단순 송금 QR코드 정보
     * @return 단숭 송금 QR 상세 정보
     */
    @PostMapping("/simple")
    public ApiResponse<SimpleQrCodeResponse> createSimpleQrCode(@RequestBody SimpleQrCodeRequest request){
        log.debug("SimpleQrCodeRequest={}", request);
        SimpleQrCodeResponse response = qrCodeService.createSimpleQrcode(request.toSimpleQrCodeDto());
        return ApiResponse.ok(response);
    }

}
