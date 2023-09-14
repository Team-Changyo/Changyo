package com.shinhan.changyo.api.service.auth;

import com.shinhan.changyo.client.request.OneTransferRequest;
import com.shinhan.changyo.client.response.OneTransferResponse;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import com.shinhan.changyo.domain.authentication.Authentication;
import com.shinhan.changyo.domain.authentication.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final AccountQueryRepository accountQueryRepository;
    private final ShinHanApiClient shinHanApiClient;

    public String generateAuthenticationNumber(String bankCode, String accountNumber) {
        String randomNumber = generateRandomNumber();
        OneTransferRequest request = OneTransferRequest.builder()
            .bankCode(bankCode)
            .accountNumber(accountNumber)
            .memo(randomNumber)
            .build();

        OneTransferResponse response = shinHanApiClient.oneTransfer(request).getData();

        Optional<Authentication> findAuthentication = authenticationRepository.findById(accountNumber);
        if (findAuthentication.isEmpty()) {
            Authentication authentication = Authentication.builder()
                .accountNumber(accountNumber)
                .authenticationNumber(randomNumber)
                .build();
            authenticationRepository.save(authentication);
        } else {
            Authentication authentication = findAuthentication.get();
            authentication.editNumber(randomNumber);
        }

        return randomNumber;
    }

    public void checkAuthenticationNumber(String accountNumber, String authenticationNumber) {
        Optional<Authentication> findAuthentication = authenticationRepository.findById(accountNumber);
        if (findAuthentication.isEmpty()) {
            throw new NoSuchElementException("재인증 요청 해주세요.");
        }

        Authentication authentication = findAuthentication.get();

        if (authentication.getLastModifiedDate().plusMinutes(3).isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("유효시간이 만료되었습니다.");
        }

        if (!authentication.getAuthenticationNumber().equals(authenticationNumber)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        authenticationRepository.deleteById(accountNumber);

        boolean result = accountQueryRepository.checkIsExistByAccountNumber(accountNumber);
        if (result) {
            throw new IllegalArgumentException("이미 등록된 계좌입니다.");
        }
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(10000);
        return String.format("%04d", number);
    }
}
