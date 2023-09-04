package com.shinhan.api.api.controller.account;

import com.shinhan.api.api.controller.ApiResponse;
import com.shinhan.api.api.controller.account.request.AccountRequest;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.api.service.account.AccountQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AccountController {

    private final AccountQueryService accountQueryService;

    @PostMapping("/v1/account/balance/detail")
    public ApiResponse<AccountResponse> getAccountBalance(@RequestBody AccountRequest request) {
        AccountResponse response = accountQueryService.getAccountBalance(request.getAccountNumber());
        log.debug("AccountResponse={}", response);
        return ApiResponse.ok(response);
    }
}


