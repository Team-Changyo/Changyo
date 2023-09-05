package com.shinhan.api.api.controller.trade;

import com.shinhan.api.api.controller.ApiResponse;
import com.shinhan.api.api.controller.trade.request.TradeRequest;
import com.shinhan.api.api.controller.trade.response.TradeResponse;
import com.shinhan.api.api.service.trade.TradeQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TradeController {

    private final TradeQueryService tradeQueryService;

    @PostMapping("/v1/search/transaction")
    public ApiResponse<TradeResponse> getTrades(@RequestBody TradeRequest request) {
        TradeResponse response = tradeQueryService.getTrade(request.getAccountNumber());
        return ApiResponse.ok(response);
    }
}
