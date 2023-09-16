package com.shinhan.changyo.api.controller.auth;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.auth.request.AuthenticationPhoneNumberRequest;
import com.shinhan.changyo.api.controller.auth.request.AuthenticationRequest;
import com.shinhan.changyo.api.controller.auth.request.CheckAuthenticationPhoneNumberRequest;
import com.shinhan.changyo.api.controller.auth.request.CheckAuthenticationRequest;
import com.shinhan.changyo.api.service.auth.AccountAuthenticationService;
import com.shinhan.changyo.api.service.auth.PhoneAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AccountAuthenticationService accountAuthenticationService;
    private final PhoneAuthenticationService phoneAuthenticationService;

    @PostMapping
    public ApiResponse<String> authenticationAccount(@Valid @RequestBody AuthenticationRequest request) {
        log.debug("call AuthenticationController#authenticationAccount");
        log.debug("AuthenticationRequest={}", request);

        String authenticationNumber = accountAuthenticationService.generateAuthenticationNumber(request.getBankCode(), request.getAccountNumber());
        log.debug("authenticationNumber={}", authenticationNumber);

        return ApiResponse.ok(authenticationNumber);
    }

    @PostMapping("/check")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<?> checkAuthenticationNumber(@Valid @RequestBody CheckAuthenticationRequest request) {
        log.debug("call AuthenticationController#checkAuthenticationNumber");
        log.debug("CheckAuthenticationRequest={}", request);

        accountAuthenticationService.checkAuthenticationNumber(request.getAccountNumber(), request.getAuthenticationNumber());

        return ApiResponse.ok(null);
    }

    @PostMapping("/phone")
    public ApiResponse<?> authenticationPhoneNumber(@Valid @RequestBody AuthenticationPhoneNumberRequest request) {
        log.debug("call AuthenticationController#authenticationPhoneNumber");
        log.debug("CheckAuthenticationRequest={}", request);

        phoneAuthenticationService.generateAuthenticationNumber(request.getPhoneNumber());

        return ApiResponse.ok(null);
    }

    @PostMapping("/phone/check")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<?> checkAuthenticationPhoneNumber(@Valid @RequestBody CheckAuthenticationPhoneNumberRequest request) {
        log.debug("call AuthenticationController#checkAuthenticationPhoneNumber");
        log.debug("CheckAuthenticationRequest={}", request);

        phoneAuthenticationService.checkAuthenticationNumber(request.getPhoneNumber(), request.getAuthenticationNumber());

        return ApiResponse.ok(null);
    }
}
