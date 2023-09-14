package com.shinhan.changyo.api.controller.trade;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.trade.request.CreateTradeRequest;
import com.shinhan.changyo.api.controller.trade.request.ReturnDepositRequest;
import com.shinhan.changyo.api.controller.trade.request.ReturnRequest;
import com.shinhan.changyo.api.controller.trade.response.*;
import com.shinhan.changyo.api.service.trade.TradeQueryService;
import com.shinhan.changyo.api.service.trade.TradeService;
import com.shinhan.changyo.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * 보증금 거래내역 관련 API 컨트롤러
 *
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;
    private final TradeQueryService tradeQueryService;


    /**
     * 보증금 송금 API
     *
     * @param request 보증금 송금 요청 정보
     * @return 보증금 거래내역 식별키
     */
    @PostMapping
    public ApiResponse<Long> createTrade(@Valid @RequestBody CreateTradeRequest request) {
        log.debug("TradeController#createTrade call");
        log.debug("CreateTradeRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long saveId = tradeService.createTrade(request.toCreateTradeDto(), loginId);
        log.debug("saveId={}", saveId);

        return ApiResponse.ok(saveId);
    }

    /**
     * 보증금 송금관리 반환대기 내역 조회 API
     *
     * @return 해당 회원의 반환대기 중인 보증금 송금 거래내역 목록
     */
    @GetMapping("/withdrawal/wait")
    public ApiResponse<WaitWithdrawalResponse> getWaitingWithdrawalTrades() {
        log.debug("TradeController#getWaitingWithdrawalTrades call");
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        WaitWithdrawalResponse response = tradeQueryService.getWaitingWithdrawalTrades(loginId);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 보증금 송금관리 반환완료 내역 조회 API
     *
     * @param lastTradeId 마지막으로 조회된 거래내역 식별키
     * @return 해당 회원의 반환완료된 보증금 송금 거래내역 목록
     */
    @GetMapping("/withdrawal/done")
    public ApiResponse<DoneWithdrawalResponse> getDoneWithdrawalTrades(@RequestParam(required = false) Long lastTradeId) {
        log.debug("TradeController#getDoneWithdrawalTrades call");
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        DoneWithdrawalResponse response = tradeQueryService.getDoneWithdrawalTrades(loginId, lastTradeId);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 보증금 정산관리 조회 API
     *
     * @param lastQrCodeId 마지막으로 조회된 QR 코드 식별키
     * @return 해당 회원의 보증금 정산관리 목록
     */
    @GetMapping("/deposit")
    public ApiResponse<DepositResponse> getDepositTrades(@RequestParam(required = false) Long lastQrCodeId) {
        log.debug("TradeController#getDepositTrades call");
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        DepositResponse response = tradeQueryService.getDepositTrades(loginId, lastQrCodeId);
        log.debug("DepositResponse={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 보증금 정산관리 상세조회 API
     *
     * @param qrCodeId     QR 코드 식별키
     * @param lastTradeId 마지막으로 조회된 QR 코드 식별키
     * @return 보증금 정산관리 상세조회 목록
     */
    @GetMapping("/deposit/detail")
    public ApiResponse<DepositDetailResponse> getDepositDetails(@RequestParam Long qrCodeId, @RequestParam(required = false) Long lastTradeId) {
        log.debug("TradeController#getDepositTradesDetail call");
        log.debug("qrCodeId={}", qrCodeId);
        log.debug("lastQrCodeId={}", lastTradeId);

        DepositDetailResponse response = tradeQueryService.getDepositDetails(qrCodeId, lastTradeId);

        return ApiResponse.ok(response);
    }

    /**
     * 보증금 반환 API (단건 / 다건 통합)
     *
     * @param request 보증금 반환 요청 객체
     * @return 반환 여부 (true: 성공 / false: 실패)
     */
    @PostMapping("/deposit")
    public ApiResponse<Boolean> returnDeposits(@Valid @RequestBody ReturnRequest request) {
        log.debug("TradeController#returnDeposit call");
        log.debug("ReturnDepositRequest={}", request);

        Boolean result = tradeService.returnDeposits(
                request.getReturnRequests()
                        .stream()
                        .map(ReturnDepositRequest::toReturnDepositDto)
                        .collect(Collectors.toList())
        );
        log.debug("result={}", result);

        return ApiResponse.ok(result);
    }

}
