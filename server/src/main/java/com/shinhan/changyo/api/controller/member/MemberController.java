package com.shinhan.changyo.api.controller.member;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.member.request.JoinRequest;
import com.shinhan.changyo.api.controller.member.request.LoginRequest;
import com.shinhan.changyo.api.controller.member.request.WithdrawalRequest;
import com.shinhan.changyo.api.controller.member.response.JoinMemberResponse;
import com.shinhan.changyo.api.controller.member.response.LoginResponse;
import com.shinhan.changyo.api.controller.member.response.MemberResponse;
import com.shinhan.changyo.api.service.member.MemberAccountService;
import com.shinhan.changyo.api.service.member.MemberService;
import com.shinhan.changyo.security.SecurityUtil;
import com.shinhan.changyo.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 계정 관련 API 컨트롤러
 * 
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberAccountService memberAccountService;

    /**
     * 회원 가입 API
     *
     * @param request 가입할 회원 정보
     * @return 가입된 회원 정보
     */
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<JoinMemberResponse> join(@Valid @RequestBody JoinRequest request) {
        log.debug("MemberController#join");
        log.debug("JoinRequest={}", request);

        JoinMemberResponse response = memberService.join(request.toJoinMemberDto());
        log.debug("JoinMemberResponse={}", response);

        return ApiResponse.created(response);
    }

    /**
     * 로그인 API
     *
     * @param request 로그인할 아이디, 비밀번호
     * @return 로그인한 회원 정보
     */
    @PostMapping("/login")
    public ApiResponse<TokenInfo> login(@Valid @RequestBody LoginRequest request) {
        log.debug("MemberController#login");
        log.debug("LoginRequest={}", request);

        TokenInfo tokenInfo = memberAccountService.login(request.getLoginId(), request.getPassword());
        log.debug("TokenInfo={}", tokenInfo);

        return ApiResponse.ok(tokenInfo);
    }

    /**
     * 회원탈퇴 API
     *
     * @param request 탈퇴할 아이디, 비밀번호
     * @return 탈퇴 성공 여부
     */
    @PostMapping("/withdrawal")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<Boolean> withdrawal(@Valid @RequestBody WithdrawalRequest request) {
        log.debug("MemberController#withdrawal");
        log.debug("WithdrawalRequest={}",request);

        Boolean result = memberService.withdrawal(request.getLoginId(), request.getPassword());
        log.debug("result={}", result);

        return ApiResponse.found(result);
    }

    @GetMapping("/info")
    public ApiResponse<MemberResponse> getInfo() {
        log.debug("MemberController#getInfo");

        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        MemberResponse response = memberService.getInfo(loginId);

        return ApiResponse.ok(response);
    }
}
