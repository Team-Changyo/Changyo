package com.shinhan.changyo.api.service.account;

import com.shinhan.changyo.api.service.account.dto.CreateAccountDto;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계좌 서비스
 * 
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    /**
     * 계쫘 등록
     * 
     * @param dto 등록할 계좌 정보
     * @return 등록된 계좌 식별키
     */
    public Long createAccount(CreateAccountDto dto) {
        return null;
    }
}
