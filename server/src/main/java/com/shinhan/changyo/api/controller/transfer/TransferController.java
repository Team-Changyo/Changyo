package com.shinhan.changyo.api.controller.transfer;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.transfer.response.SimpleTransferInfoResponse;
import com.shinhan.changyo.api.controller.transfer.response.TransferInfoResponse;
import com.shinhan.changyo.api.service.transfer.TransferQueryService;
import com.shinhan.changyo.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이체 관련 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferQueryService transferQueryService;

    /**
     * 보증금 송금 정보 조회 API
     *
     * @param qrCodeId QR 코드 식별키
     * @return 보증금 이체 정보
     */
    @GetMapping
    public ApiResponse<TransferInfoResponse> getTransferInfo(@RequestParam Long qrCodeId) {
        log.debug("TransferController#getQrTransferInfo call");
        log.debug("qrCodeId={}", qrCodeId);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        TransferInfoResponse response = transferQueryService.getTransferInfo(qrCodeId, loginId);
        log.debug("TransferResponse={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 간편송금 정보 조회 API
     *
     * @param simpleQrCodeId 간편송금 QR 코드 식별키
     * @return 간편송금 이체 정보
     */
    @GetMapping("/simple")
    public ApiResponse<SimpleTransferInfoResponse> getSimpleTransferInfo(@RequestParam Long simpleQrCodeId) {
        log.debug("TransferController#getSimpleQrTransferInfo call");
        log.debug("simpleQrCodeId={}", simpleQrCodeId);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        SimpleTransferInfoResponse response = transferQueryService.getSimpleTransferInfo(simpleQrCodeId, loginId);
        log.debug("SimpleTransferInfoResponse={}", response);

        return ApiResponse.ok(response);
    }
}
