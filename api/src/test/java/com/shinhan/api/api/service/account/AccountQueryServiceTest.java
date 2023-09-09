package com.shinhan.api.api.service.account;

import com.shinhan.api.IntegrationTestSupport;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.api.controller.account.response.CustomerNameResponse;
import com.shinhan.api.domain.account.Account;
import com.shinhan.api.domain.account.repository.AccountRepository;
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

    @DisplayName("은행 코드와 계좌 번로를 받아 예금주 실명을 조회한다.")
    @Test
    void getCustomerName() {
        //given
        Account account = createAccount();

        //when
        CustomerNameResponse response = accountQueryService.getCustomerName(account.getBankCode(), account.getAccountNumber());

        //then
        assertThat(response.getCustomerName()).isEqualTo("김신한");
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