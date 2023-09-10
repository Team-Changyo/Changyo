package com.shinhan.changyo.api.service.account;

import com.shinhan.changyo.api.controller.account.response.AccountDetailResponse;
import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
     * 회원별 계좌 전체 조회
     *
     * @param memberId 계좌 조회할 회원 식별키
     * @return 계좌 개수, 계좌 정보 목록
     */
    public AccountResponse getAccounts(Long memberId) {
        List<AccountDetailResponse> accounts = accountQueryRepository.getAccountsByMemberId(memberId);
        checkIsEmpty(accounts);
        return AccountResponse.of(accounts, accounts.size());
    }

    /**
     * 등록 계좌 존재 여부 확인
     *
     * @param accounts 등록된 계좌 목록
     * @throws NoSuchElementException 등록된 계좌 목록이 비어있거나 NULL 일 경우
     */
    private void checkIsEmpty(List<AccountDetailResponse> accounts) {
        if (accounts == null || accounts.isEmpty()) {
            throw new NoSuchElementException("등록된 계좌가 없습니다.");
        }
    }
}