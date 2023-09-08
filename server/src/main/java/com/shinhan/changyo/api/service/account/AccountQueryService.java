package com.shinhan.changyo.api.service.account;

import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계좌 쿼리 서비스
 * 
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountQueryService {
    private final AccountQueryRepository accountQueryRepository;

    /**
     * 계좌 전체 조회
     *
     * @param memberId 계좌 조회할 회원 식별키
     * @return 계좌 개수, 계좌 정보 목록
     */
    public AccountResponse getAccounts(Long memberId) {
        return null;
    }
}
