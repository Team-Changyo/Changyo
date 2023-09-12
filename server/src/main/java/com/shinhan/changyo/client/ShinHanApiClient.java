package com.shinhan.changyo.client;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.client.request.BalanceRequest;
import com.shinhan.changyo.client.request.OneTransferRequest;
import com.shinhan.changyo.client.request.TradeRequest;
import com.shinhan.changyo.client.request.TransferRequest;
import com.shinhan.changyo.client.response.BalanceResponse;
import com.shinhan.changyo.client.response.OneTransferResponse;
import com.shinhan.changyo.client.response.TradeResponse;
import com.shinhan.changyo.client.response.TransferResponse;
import com.shinhan.changyo.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "shinhan-api", url = "localhost:8081", configuration = FeignConfig.class)
public interface ShinHanApiClient {

    @PostMapping(produces = "application/json", value = "/v1/account/balance/detail")
    ApiResponse<BalanceResponse> getAccountBalance(@RequestBody BalanceRequest request);

    @PostMapping(produces = "application/json", value = "/v1/auth/1transfer")
    ApiResponse<OneTransferResponse> oneTransfer(@RequestBody OneTransferRequest request);

    @PostMapping(produces = "application/json", value = "/v1/transfer/krw")
    ApiResponse<TransferResponse> transfer(@RequestBody TransferRequest request);

    @PostMapping(produces = "application/json", value = "/v1/search/transaction")
    ApiResponse<TradeResponse> trade(@RequestBody TradeRequest request);
}
