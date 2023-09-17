package com.shinhan.changyo.api.controller.account;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.account.request.AccountTradeRequest;
import com.shinhan.changyo.api.controller.account.request.CreateAccountRequest;
import com.shinhan.changyo.api.controller.account.request.EditAccountTitleRequest;
import com.shinhan.changyo.api.controller.account.response.AccountEditResponse;
import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.api.controller.account.response.AccountTradeAllResponse;
import com.shinhan.changyo.api.controller.account.request.AccountRequest;
import com.shinhan.changyo.api.service.account.AccountQueryService;
import com.shinhan.changyo.api.service.account.AccountService;
import com.shinhan.changyo.security.SecurityUtil;
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
     * 계좌 등록 API
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
     * 회원별 계좌 전체 조회 API
     *
     * @return 계좌 개수, 계좌 정보 목록
     */
    @GetMapping
    public ApiResponse<AccountResponse> getAccounts() {
        log.debug("AccountController#getAccounts called");

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        AccountResponse response = accountQueryService.getAccounts(loginId);
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    /**
     * 계좌내역 상세 전체 조회(내역 조회) API
     *
     * @param request 계좌정보
     * @return 계좌내역 상세 목록
     */
    @PostMapping("/detail")
    public ApiResponse<AccountTradeAllResponse> getAccountDetailAll(@Valid @RequestBody AccountTradeRequest request) {
        log.debug("AccountController#getAccountDetailAll called");
        log.debug("AccountRequest={}", request);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        AccountTradeAllResponse response = accountQueryService.getAccountTradeAll(request.toAccountTradeDto(loginId), 0);

        return ApiResponse.ok(response);
    }

    /**
     * 입금 계좌내역 상세 전체 조회(내역 조회) API
     *
     * @param request 계좌 정보
     * @return 입금 계좌내역 상세
     */
    @PostMapping("/deposit")
    public ApiResponse<AccountTradeAllResponse> getAccountDetailDeposit(@Valid @RequestBody AccountTradeRequest request) {
        log.debug("AccountController#getAccountDetailDeposit called");
        log.debug("AccountRequest={}", request);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        AccountTradeAllResponse response = accountQueryService.getAccountTradeAll(request.toAccountTradeDto(loginId), 1);

        return ApiResponse.ok(response);
    }

    /**
     * 출금 계좌내역 상세 전체 조회(내역 조회) API
     *
     * @param request 게좌 정보
     * @return 출금 계좌내역 상세 목록
     */
    @PostMapping("/withdrawal")
    public ApiResponse<AccountTradeAllResponse> getAccountDetailWithdrawal(@Valid @RequestBody AccountTradeRequest request) {
        log.debug("AccountController#getAccountDetailWithdrawal called");
        log.debug("AccountRequest={}", request);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        AccountTradeAllResponse response = accountQueryService.getAccountTradeAll(request.toAccountTradeDto(loginId), 2);

        return ApiResponse.ok(response);
    }


    /**
     * 계좌 별명 수정 API
     *
     * @param request 수정 계좌 정보
     * @return 수정된 계좌 정보
     */
    @PatchMapping("/title")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<AccountEditResponse> editTitle(@RequestBody EditAccountTitleRequest request) {
        log.debug("AccountController#editTitle called");
        log.debug("EditAccountTitleRequest={}", request);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        AccountEditResponse response = accountService.editTitle(request.toEditAccountTitleDto(loginId));

        return ApiResponse.found(response);
    }


    /**
     * 주계좌 여부 수정 API
     *
     * @param request 수정 계좌 정보
     * @return 수정된 계좌 정보
     */
    @PatchMapping("/main-account")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<AccountEditResponse> editMainAccount(@RequestBody AccountRequest request) {
        log.debug("AccountController#editMainAccount called");
        log.debug("AccountRequest={}", request);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        AccountEditResponse response = accountService.editMainAccount(request.toAccountDto(loginId));

        return ApiResponse.found(response);
    }

    /**
     * 계좌 삭제 API
     *
     * @param request 삭제 계좌 정보
     * @return true: 삭제 성공 시 false: 삭제 실패시
     */
    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Boolean> removeAccount(@RequestBody AccountRequest request) {
        log.debug("AccountController#removeAccount called");
        log.debug("AccountRequest={}", request);

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Boolean result = accountService.removeAccount(request.toAccountDto(loginId));

        return ApiResponse.found(result);
    }

    /**
     * 주계좌 소유 여부 확인 API
     *
     * @return true: 로그인한 회원이 주계좌 소유시 false: 주계좌를 소유하지 않은 경우
     */
    @GetMapping("/main")
    public ApiResponse<Boolean> hasMainAccount() {
        log.debug("AccountController#hasMainAccount called");

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Boolean result = accountQueryService.hasMainAccount(loginId);

        return ApiResponse.ok(result);
    }
}
