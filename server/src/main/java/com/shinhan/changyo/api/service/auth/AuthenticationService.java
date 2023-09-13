package com.shinhan.changyo.api.service.auth;

import com.shinhan.changyo.client.request.OneTransferRequest;
import com.shinhan.changyo.client.response.OneTransferResponse;
import com.shinhan.changyo.client.ShinHanApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final ShinHanApiClient shinHanApiClient;

    public String generateAuthenticationNumber(String bankCode, String accountNumber) {
        String randomNumber = generateRandomNumber();
        OneTransferRequest request = OneTransferRequest.builder()
            .bankCode(bankCode)
            .accountNumber(accountNumber)
            .memo(randomNumber)
            .build();
        OneTransferResponse response = shinHanApiClient.oneTransfer(request).getData();
        return randomNumber;
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(1000);
        return String.format("%03d", number);
    }
}
