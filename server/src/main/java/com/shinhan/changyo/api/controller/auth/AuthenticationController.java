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
    public ApiResponse<String> authenticationAccount(@Valid @RequestBody AuthenticationRequest request) {
        log.debug("call AuthenticationController#authenticationAccount");
        log.debug("AuthenticationRequest={}", request);

        String authenticationNumber = authenticationService.generateAuthenticationNumber(request.getBankCode(), request.getAccountNumber());
        log.debug("authenticationNumber={}", authenticationNumber);

        return ApiResponse.ok(authenticationNumber);
    }

    @PostMapping("/check")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<?> checkAuthenticationNumber(@Valid @RequestBody CheckAuthenticationRequest request) {
        log.debug("call AuthenticationController#checkAuthenticationNumber");
        log.debug("CheckAuthenticationRequest={}", request);

        authenticationService.checkAuthenticationNumber(request.getAccountNumber(), request.getAuthenticationNumber());

        return ApiResponse.ok(null);
    }
}
