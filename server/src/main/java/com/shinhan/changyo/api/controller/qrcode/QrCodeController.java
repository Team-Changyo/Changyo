package com.shinhan.changyo.api.controller.qrcode;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.qrcode.request.EditAmountRequest;
import com.shinhan.changyo.api.controller.qrcode.request.EditTitleRequest;
import com.shinhan.changyo.api.controller.qrcode.request.QrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.request.SimpleQrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeDetailResponse;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeResponses;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeResponse;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
import com.shinhan.changyo.api.service.qrcode.QrCodeQueryService;
import com.shinhan.changyo.api.service.qrcode.QrCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // TODO: 2023-09-09 홍진식 :  목록으로 안가고 qr코드 정보 바로 상세 조회
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<QrCodeDetailResponse> createQrCode(@RequestBody QrCodeRequest request){
        log.debug("QrCodeRequest={}", request);
        QrCodeDetailResponse response = qrCodeService.createQrcode(request.toQrCodeDto());
        return ApiResponse.created(response);
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


    // TODO: 2023-09-09 홍진식 Pathvariable 넣어줘야하는지, request에 포함 시킬지
    /**
     * 보증금 QR코드 금액 변경
     *
     * @param qrCodeId QR코드 식별키
     * @param request 변경할 금액 정보
     * @return 변경된 QR코드 정보
     */
    @PatchMapping("/amount/{qrCodeId}")
    public ApiResponse<QrCodeDetailResponse> editAmount(@PathVariable Long qrCodeId, @RequestBody EditAmountRequest request){
        log.debug("qrCodeId={}", qrCodeId);
        log.debug("AmountRequest={}", request);
        QrCodeDetailResponse response = qrCodeService.editAmount(request.toEditAmountDto(qrCodeId));
        return ApiResponse.found(response);
    }

    /**
     * 보증금 QR코드 title 변경
     *
     * @param qrCodeId, QR코드 식별키
     * @param request 변경할 제목 정보
     * @return 변경된 QR코드 정보
     */
    @PatchMapping("/title/{qrCodeId}")
    public ApiResponse<QrCodeDetailResponse> editTitle(@PathVariable Long qrCodeId, @RequestBody EditTitleRequest request){
        log.debug("qrCodeId={}", qrCodeId);
        log.debug("EditTitleRequest={}", request);
        QrCodeDetailResponse response = qrCodeService.editTitle(request.toEditTitleDto(qrCodeId));
        return ApiResponse.found(response);
    }


    /**
     * QR코드 삭제
     *
     * @param qrCodeId QR코드 식별키
     * @return 삭제 성공 여부(성공 : true, 실패 : false)
     */

    @DeleteMapping("/remove/{qrCodeId}")
    public ApiResponse<Boolean> removeQrCode(@PathVariable Long qrCodeId){
        log.debug("qrCodeId={}", qrCodeId);
        Boolean result = qrCodeService.removeQrCode(qrCodeId);
        return ApiResponse.found(result);
    }

    /**
     * QR코드 목록 전체 조회
     *
     * @return QR코드 목록
     */

    // TODO: 2023-09-09 홍진식 : 회원 join해서 해당 회원의 모든 account 가져오고 조회해야함
    @GetMapping()
    public ApiResponse<QrCodeResponses> getQrCodes(@RequestHeader("memberId") Long memberId){
        log.debug("memberId={}", memberId);
        QrCodeResponses responses = qrCodeQueryService.getQrCodes(memberId);
        return ApiResponse.ok(responses);
    }

    /**
     * 보증금 QR코드 상세 조회
     *
     * @param qrCodeId QR코드 식별키
     * @return QR코드 정보
     */

    @GetMapping("/{qrCodeId}")
    public ApiResponse<QrCodeDetailResponse> getQrCode(@PathVariable Long qrCodeId){
        log.debug("qrCodeId={}", qrCodeId);
        QrCodeDetailResponse response = qrCodeService.getQrCode(qrCodeId);
        return ApiResponse.ok(response);
    }

}
