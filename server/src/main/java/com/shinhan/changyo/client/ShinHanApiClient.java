package com.shinhan.changyo.client;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.service.account.dto.BalanceRequest;
import com.shinhan.changyo.api.service.account.dto.BalanceResponse;
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
}
