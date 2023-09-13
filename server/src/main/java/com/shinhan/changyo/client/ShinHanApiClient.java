package com.shinhan.changyo.client;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.client.request.*;
import com.shinhan.changyo.client.response.*;
import com.shinhan.changyo.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "shinhan-api", url = "https://j9c205.p.ssafy.io/shinhan/api/", configuration = FeignConfig.class)
public interface ShinHanApiClient {
    @PostMapping(produces = "application/json", value = "/v1/account")
    ApiResponse<DetailResponse> getAccountDetail(@RequestBody AccountDetailRequest request);

    @PostMapping(produces = "application/json", value = "/v1/account/balance/detail")
    ApiResponse<BalanceResponse> getAccountBalance(@RequestBody BalanceRequest request);

    @PostMapping(produces = "application/json", value = "/v1/auth/1transfer")
    ApiResponse<OneTransferResponse> oneTransfer(@RequestBody OneTransferRequest request);

    @PostMapping(produces = "application/json", value = "/v1/transfer/krw")
    ApiResponse<TransferResponse> transfer(@RequestBody TransferRequest request);

    @PostMapping(produces = "application/json", value = "/v1/search/transaction")
    ApiResponse<TradeResponse> trade(@RequestBody TradeRequest request);
}
