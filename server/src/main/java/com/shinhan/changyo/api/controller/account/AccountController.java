package com.shinhan.changyo.api.controller.account;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.account.request.CreateAccountRequest;
import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.api.service.account.AccountQueryService;
import com.shinhan.changyo.api.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 계좌 관련 API 컨트롤러
 * 
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountQueryService accountQueryService;

    /**
     * 계좌 등록
     *
     * @param request 등록할 계좌정보
     * @return 등록된 계좌 식별키
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        log.debug("AccountController#createAccount called");
        log.debug("CreateAccountRequest={}", request);

        Long saveId = accountService.createAccount(request.toCreateAccountDto());
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 회원별 계좌 전체 조회
     *
     * @param memberId 계좌 조회할 회원 식별키
     * @return 계좌 개수, 계좌 정보 목록
     */
    @GetMapping()
    public ApiResponse<AccountResponse> getAccounts(@RequestParam String memberId) {
        log.debug("AccountController#getAccounts called");
        log.debug("memberId={}", memberId);

        AccountResponse response = accountQueryService.getAccounts(Long.parseLong(memberId));
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
