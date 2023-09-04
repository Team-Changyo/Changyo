package com.shinhan.api.api.service.account;

import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.domain.account.repository.AccountQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountQueryService {

    private final AccountQueryRepository accountQueryRepository;

    public AccountResponse getAccountBalance(String accountNumber) {
        return accountQueryRepository.getAccountBalance(accountNumber);
    }
}
