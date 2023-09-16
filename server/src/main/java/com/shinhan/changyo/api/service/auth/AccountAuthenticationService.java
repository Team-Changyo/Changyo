package com.shinhan.changyo.api.service.auth;

import com.shinhan.changyo.client.request.OneTransferRequest;
import com.shinhan.changyo.client.response.OneTransferResponse;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AccountAuthenticationService {

    private final AccountQueryRepository accountQueryRepository;
    private final ShinHanApiClient shinHanApiClient;

    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;

    public AccountAuthenticationService(AccountQueryRepository accountQueryRepository, ShinHanApiClient shinHanApiClient, RedisTemplate<String, String> redisTemplate) {
        this.accountQueryRepository = accountQueryRepository;
        this.shinHanApiClient = shinHanApiClient;
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    public String generateAuthenticationNumber(String bankCode, String accountNumber) {
        String randomNumber = generateRandomNumber();

        OneTransferRequest request = OneTransferRequest.builder()
            .bankCode(bankCode)
            .accountNumber(accountNumber)
            .memo(randomNumber)
            .build();
        OneTransferResponse response = shinHanApiClient.oneTransfer(request).getData();

        valueOperations.set(accountNumber, randomNumber);

        Boolean expire = redisTemplate.expire(accountNumber, 3, TimeUnit.MINUTES);

        return randomNumber;
    }

    public void checkAuthenticationNumber(String accountNumber, String authenticationNumber) {
        String number = valueOperations.get(accountNumber);
        if (number == null) {
            throw new IllegalArgumentException("유효시간이 만료되었습니다.");
        }

        if (!number.equals(authenticationNumber)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        redisTemplate.expire(accountNumber, 0, TimeUnit.SECONDS);

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
