package com.shinhan.changyo.api.controller.auth;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.auth.request.AuthenticationRequest;
import com.shinhan.changyo.api.controller.auth.request.CheckAuthenticationRequest;
import com.shinhan.changyo.api.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ApiResponse<String> authenticationAccount(@Valid @RequestBody AuthenticationRequest jsonRequest, HttpServletRequest request) {
        String authenticationNumber = authenticationService.generateAuthenticationNumber(jsonRequest.getBankCode(), jsonRequest.getAccountNumber());

        HttpSession session = request.getSession();
        session.setAttribute("authenticationNumber", authenticationNumber);
        session.setMaxInactiveInterval(180);

        return ApiResponse.ok(authenticationNumber);
    }

    @PostMapping("/check")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<?> checkAuthenticationNumber(@Valid @RequestBody CheckAuthenticationRequest jsonRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String authenticationNumber = (String) session.getAttribute("authenticationNumber");

        if (!jsonRequest.getAuthenticationNumber().equals(authenticationNumber)) {
            throw new IllegalArgumentException("인증에 실패했습니다.");
        }

        session.invalidate();

        return ApiResponse.ok(null);
    }
}
