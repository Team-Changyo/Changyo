package com.shinhan.changyo.api.controller.trade;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.trade.request.CreateTradeRequest;
import com.shinhan.changyo.api.controller.trade.response.WithdrawalResponse;
import com.shinhan.changyo.api.service.trade.TradeQueryService;
import com.shinhan.changyo.api.service.trade.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

        Long saveId = tradeService.createTrade(request.toCreateTradeDto());
        log.debug("saveId={}", saveId);

        return ApiResponse.ok(saveId);
    }

    /**
     * 송금내역 목록 조회 API
     *
     * @param memberId 회원 식별키
     * @return 해당 회원의 송금내역 목록
     */
    @GetMapping
    public ApiResponse<WithdrawalResponse> getWithdrawalTrades(@RequestHeader(name = "memberId") Long memberId) {
        log.debug("TradeController#getWithdrawalTrades call");
        log.debug("memberId={}", memberId);

        WithdrawalResponse response = tradeQueryService.getWithdrawalTrades(memberId);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
