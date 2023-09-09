package com.shinhan.api.api.controller.account;

import com.shinhan.api.api.controller.ApiResponse;
import com.shinhan.api.api.controller.account.request.AccountRequest;
import com.shinhan.api.api.controller.account.request.CustomerNameRequest;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.api.controller.account.response.CustomerNameResponse;
import com.shinhan.api.api.service.account.AccountQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AccountController {

    private final AccountQueryService accountQueryService;

    /**
     * 계좌잔액조회
     */
    @PostMapping("/v1/account/balance/detail")
    public ApiResponse<AccountResponse> getAccountBalance(@Valid @RequestBody AccountRequest request) {
        log.debug("call AccountController#getAccountBalance");
        log.debug("accountNumber={}", request.getAccountNumber());

        AccountResponse response = accountQueryService.getAccountBalance(request.getAccountNumber());
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 예금주 실명조회
     */
    @PostMapping("/v1/search/name")
    public ApiResponse<CustomerNameResponse> getCustomerName(@RequestBody CustomerNameRequest request) {
        CustomerNameResponse response = accountQueryService.getCustomerName(request.getBankCode(), request.getAccountNumber());
        return ApiResponse.ok(response);
    }
}


