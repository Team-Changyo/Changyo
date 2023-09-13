package com.shinhan.api.api.service.account;

import com.shinhan.api.api.controller.account.response.AccountDetailResponse;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.api.controller.account.response.CustomerNameResponse;
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

    public CustomerNameResponse getCustomerName(String bankCode, String accountNumber) {
        return accountQueryRepository.getCustomerName(bankCode, accountNumber);
    }

    public AccountDetailResponse getAccountDetail(String accountNumber) {
        return accountQueryRepository.getAccountDetail(accountNumber);
    }
}
