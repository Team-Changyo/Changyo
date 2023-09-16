package com.shinhan.changyo.api.controller.qrcode;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.qrcode.request.EditAmountRequest;
import com.shinhan.changyo.api.controller.qrcode.request.EditTitleRequest;
import com.shinhan.changyo.api.controller.qrcode.request.QrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.request.SimpleQrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeDetailResponse;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeResponses;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
import com.shinhan.changyo.api.service.qrcode.QrCodeQueryService;
import com.shinhan.changyo.api.service.qrcode.QrCodeService;
import com.shinhan.changyo.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/qrcode-management/qrcode")
public class QrCodeController {

    private final QrCodeQueryService qrCodeQueryService;
    private final QrCodeService qrCodeService;

    /**
     * 보증금 QR코드 생성 요청
     *
     * @param request 등록할 보증금 QR코드 정보
     * @return 등록된 QR코드  상세 정보
     */
    // TODO: 2023-09-09 홍진식 :  목록으로 안가고 qr코드 정보 바로 상세 조회
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<QrCodeDetailResponse> createQrCode(@RequestBody QrCodeRequest request) {
        log.debug("QrCodeRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        QrCodeDetailResponse response = qrCodeService.createQrcode(request.toQrCodeDto(loginId));
        return ApiResponse.created(response);
    }

    /**
     * 단순 송금 QR코드 생성 요청
     *
     * @param request 생성할 단순 송금 QR코드 정보
     * @return 단숭 송금 QR 상세 정보
     */
    @PostMapping("/simple")
    public ApiResponse<SimpleQrCodeResponse> createSimpleQrCode(@Valid @RequestBody SimpleQrCodeRequest request) {
        log.debug("SimpleQrCodeRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        SimpleQrCodeResponse response = qrCodeService.createSimpleQrcode(request.toSimpleQrCodeDto(), loginId);
        return ApiResponse.ok(response);
    }


    /**
     * 보증금 QR코드 금액 변경
     *
     * @param qrCodeId QR코드 식별키
     * @param request  변경할 금액 정보
     * @return 변경된 QR코드 정보
     */
    @PatchMapping("/amount/{qrCodeId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<QrCodeDetailResponse> editAmount(@PathVariable Long qrCodeId, @RequestBody EditAmountRequest request) {
        log.debug("qrCodeId={}", qrCodeId);
        log.debug("AmountRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        QrCodeDetailResponse response = qrCodeService.editAmount(request.toEditAmountDto(qrCodeId, loginId));
        return ApiResponse.found(response);
    }

    /**
     * 보증금 QR코드 title 변경
     *
     * @param qrCodeId, QR코드 식별키
     * @param request   변경할 제목 정보
     * @return 변경된 QR코드 정보
     */
    @PatchMapping("/title/{qrCodeId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<QrCodeDetailResponse> editTitle(@PathVariable Long qrCodeId, @RequestBody EditTitleRequest request) {
        log.debug("qrCodeId={}", qrCodeId);
        log.debug("EditTitleRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        QrCodeDetailResponse response = qrCodeService.editTitle(request.toEditTitleDto(qrCodeId, loginId));
        return ApiResponse.found(response);
    }


    /**
     * QR코드 삭제
     *
     * @param qrCodeId QR코드 식별키
     * @return 삭제 성공 여부(성공 : true, 실패 : false)
     */

    @DeleteMapping("/remove/{qrCodeId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Boolean> removeQrCode(@PathVariable Long qrCodeId) {
        log.debug("qrCodeId={}", qrCodeId);
        Boolean result = qrCodeService.removeQrCode(qrCodeId);
        return ApiResponse.found(result);
    }

    /**
     * QR코드 목록 전체 조회
     *
     * @return QR코드 목록
     */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<QrCodeResponses> getQrCodes() {
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        QrCodeResponses responses = qrCodeQueryService.getQrCodes(loginId);
        return ApiResponse.ok(responses);
    }

    /**
     * 보증금 QR코드 상세 조회
     *
     * @param qrCodeId QR코드 식별키
     * @return QR코드 정보
     */

    @GetMapping("/{qrCodeId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<QrCodeDetailResponse> getQrCode(@PathVariable Long qrCodeId) {
        log.debug("qrCodeId={}", qrCodeId);
        QrCodeDetailResponse response = qrCodeService.getQrCode(qrCodeId);
        return ApiResponse.ok(response);
    }

}
