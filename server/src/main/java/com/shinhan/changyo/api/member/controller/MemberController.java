package com.shinhan.changyo.api.member.controller;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.member.controller.request.JoinRequest;
import com.shinhan.changyo.api.member.controller.response.JoinMemberResponse;
import com.shinhan.changyo.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 회원 가입 API
     *
     * @param request 가입할 회원 정보
     * @return 가입된 회원 정보
     */
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<JoinMemberResponse> join(@Valid @RequestBody JoinRequest request) {
        log.debug("MemberController join");
        log.debug("JoinRequest={}", request);

        JoinMemberResponse response = memberService.join(request.toJoinMemberDto());
        log.debug("JoinMemberResponse={}", response);

        return ApiResponse.created(response);
    }
}
