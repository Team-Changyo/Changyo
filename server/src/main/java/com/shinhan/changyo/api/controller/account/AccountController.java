package com.shinhan.changyo.api.controller.account;

import com.shinhan.changyo.api.ApiControllerAdvice;
import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.account.request.CreateAccountRequest;
import com.shinhan.changyo.api.controller.account.request.EditAccountTitleRequest;
import com.shinhan.changyo.api.controller.account.response.AccountDetailResponse;
import com.shinhan.changyo.api.controller.account.response.AccountEditResponse;
import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.api.controller.account.response.AccountTradeAllResponse;
import com.shinhan.changyo.api.service.account.AccountQueryService;
import com.shinhan.changyo.api.service.account.AccountService;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        Long saveId = accountService.createAccount(request, loginId);
        log.debug("saveId={}", saveId);

        return ApiResponse.created(saveId);
    }

    /**
     * 회원별 계좌 전체 조회
     *
     *
     * @return 계좌 개수, 계좌 정보 목록
     */
    @GetMapping()
    public ApiResponse<AccountResponse> getAccounts() {
        log.debug("AccountController#getAccounts called");
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AccountResponse response = accountQueryService.getAccounts(loginId);
        log.debug("response={}", response);
        return ApiResponse.ok(response); // -> 성공 코드
    }

    /**
     * 계좌내역 상세 전체 조회(내역 조회)
     *
     * @param accountId
     * @return
     */
    @GetMapping("/{accountId}")
    public ApiResponse<AccountTradeAllResponse> getAccountDetailAll(@PathVariable Long accountId){
        log.debug("AccountController#getAccountDetailAll called");
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AccountTradeAllResponse response = accountQueryService.getAccountTradeAll(loginId,accountId);
        return ApiResponse.ok(response);
    }

    /**
     * 입금 계좌내역 상세 전체 조회(내역 조회)
     *
     * @param accountId
     * @return
     */
    @GetMapping("/deposit/{accountId}")
    public ApiResponse<AccountTradeAllResponse> getAccountDetailDeposit(@PathVariable Long accountId){
        log.debug("AccountController#getAccountDetailDeposit called");
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AccountTradeAllResponse response = accountQueryService.getAccountTradeDeposit(loginId,accountId);
        return ApiResponse.ok(response);
    }

    /**
     * 출금 계좌내역 상세 전체 조회(내역 조회)
     *
     * @param accountId
     * @return
     */

    @GetMapping("/withdrawal/{accountId}")
    public ApiResponse<AccountTradeAllResponse> getAccountDetailWithdrawal(@PathVariable Long accountId){
        log.debug("AccountController#getAccountDetailWithdrawal called");
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AccountTradeAllResponse response = accountQueryService.getAccountTradeWithdrawal(loginId,accountId);
        return ApiResponse.ok(response);
    }


    @PatchMapping("/title/{accountId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<AccountEditResponse> editTitle(@PathVariable Long accountId, @RequestBody EditAccountTitleRequest request){
        log.debug("request={}",request);
        log.debug("accountId={}", accountId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AccountEditResponse response = accountService.editTitle(request.toEditAccountTitleDto(accountId, loginId));
        return ApiResponse.found(response);
    }


    @PatchMapping("/main-account/{accountId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<AccountEditResponse> editMainAccount(@PathVariable Long accountId){
        log.debug("accountId={}", accountId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AccountEditResponse response = accountService.editMainAccount(accountId, loginId);
        return ApiResponse.found(response);
    }

    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Boolean> removeAccount(@PathVariable Long accountId){
        log.debug("accountId={}", accountId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        Boolean response = accountService.removeAccount(accountId, loginId);
        return ApiResponse.found(response);
    }
}
