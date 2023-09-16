package com.shinhan.changyo.api.service.auth;

import com.shinhan.changyo.client.request.OneTransferRequest;
import com.shinhan.changyo.client.response.OneTransferResponse;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import com.shinhan.changyo.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountAuthenticationService {

    private final AccountQueryRepository accountQueryRepository;
    private final ShinHanApiClient shinHanApiClient;
    private final RedisRepository redisRepository;

    public String generateAuthenticationNumber(String bankCode, String accountNumber) {
        String randomNumber = GenerateRandomNumber.generateRandomNumber(4);

        sendOneTransfer(bankCode, accountNumber, randomNumber);

        Boolean expired = redisRepository.save(accountNumber, randomNumber, 3);

        return randomNumber;
    }

    public void checkAuthenticationNumber(String accountNumber, String authenticationNumber) {
        String number = redisRepository.findByKey(accountNumber);
        validateExpiredTime(number);

        validateEqualNumber(authenticationNumber, number);

        redisRepository.expire(accountNumber);

        validateDuplicateAccountNumber(accountNumber);
    }

    private void sendOneTransfer(String bankCode, String accountNumber, String randomNumber) {
        OneTransferRequest request = OneTransferRequest.builder()
            .bankCode(bankCode)
            .accountNumber(accountNumber)
            .memo(randomNumber)
            .build();
        OneTransferResponse response = shinHanApiClient.oneTransfer(request).getData();
    }

    private void validateExpiredTime(String number) {
        if (number == null) {
            throw new IllegalArgumentException("유효시간이 만료되었습니다.");
        }
    }

    private void validateEqualNumber(String authenticationNumber, String number) {
        if (!number.equals(authenticationNumber)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }
    }

    private void validateDuplicateAccountNumber(String accountNumber) {
        boolean result = accountQueryRepository.checkIsExistByAccountNumber(accountNumber);
        if (result) {
            throw new IllegalArgumentException("이미 등록된 계좌입니다.");
        }
    }
}
