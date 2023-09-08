package com.shinhan.changyo.api.service.account;

import com.shinhan.changyo.api.service.account.dto.CreateAccountDto;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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
    private final MemberRepository memberRepository;

    /**
     * 계좌 등록
     * 
     * @param dto 등록할 계좌 정보
     * @return 등록된 계좌 식별키
     */
    public Long createAccount(CreateAccountDto dto) {
        Member member = getMember(dto.getMemberId());
        
        // TODO: 2023-09-09 최영환 신한 API 에 잔액 조회 요청 보내서 처리해야함
        Account account = dto.toEntity(member);

        return accountRepository.save(account).getId();
    }

    /**
     * 회원 엔티티 조회
     * @param memberId 조회할 회원 식별키
     * @return 조회된 회원
     * @throws NoSuchElementException 조회하려는 회원이 존재하지 않는 경우
     */
    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
    }
}
