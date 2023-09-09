package com.shinhan.api.api.service.account;

import com.shinhan.api.IntegrationTestSupport;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.domain.account.Account;
import com.shinhan.api.domain.account.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class AccountQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private AccountQueryService accountQueryService;

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("계좌 번호를 받아 잔액을 조회한다.")
    @Test
    void getAccountBalance() {
        //given
        Account account = createAccount();

        //when
        AccountResponse response = accountQueryService.getAccountBalance(account.getAccountNumber());

        //then
        assertThat(response.getBalance()).isEqualTo(331551);
    }

    private Account createAccount() {
        Account account = Account.builder()
            .accountNumber("110184999999")
            .balance(331551)
            .productName("예금")
            .customerName("김신한")
            .bankCode("088")
            .build();
        return accountRepository.save(account);
    }
}