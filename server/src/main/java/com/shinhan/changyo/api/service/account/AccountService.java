package com.shinhan.changyo.api.service.account;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.client.BalanceRequest;
import com.shinhan.changyo.client.BalanceResponse;
import com.shinhan.changyo.api.service.account.dto.CreateAccountDto;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final AccountQueryRepository accountQueryRepository;
    private final MemberRepository memberRepository;
    private final ShinHanApiClient shinHanApiClient;

    /**
     * 계좌 등록
     *
     * @param dto 등록할 계좌 정보
     * @return 등록된 계좌 식별키
     */
    public Long createAccount(CreateAccountDto dto) {
        Member member = getMember(dto.getMemberId());

        Account savedAccount = saveAccount(dto, member);

        return savedAccount.getId();
    }

    /**
     * 회원 엔티티 조회
     *
     * @param memberId 조회할 회원 식별키
     * @return 조회된 회원
     * @throws NoSuchElementException 조회하려는 회원이 존재하지 않는 경우
     */
    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
    }

    /**
     * 계좌 엔티티 생성 및 DB 저장
     *
     * @param dto    등록할 계좌 정보
     * @param member 회원 엔티티
     * @return 등록된 계좌 엔티티
     */
    private Account saveAccount(CreateAccountDto dto, Member member) {
        int balance = getBalance(dto.getAccountNumber());

        Account account = dto.toEntity(member, balance);

        if (checkIsFirstAccount(member.getId())) {
            account.setMainAccount();
        }

        return accountRepository.save(account);
    }

    /**
     * 첫 등록 계좌 여부 확인
     *
     * @param memberId 회원 식별키
     * @return true: 첫 등록 계좌인 경우. false: 첫 등록 계좌가 아닌 경우
     */
    private Boolean checkIsFirstAccount(Long memberId) {
        Integer accountSize = accountQueryRepository.getAccountSizeByMemberId(memberId);
        return accountSize == null || accountSize == 0;
    }

    /**
     * 신한은행 계좌 잔액 조회 결과 반환
     *
     * @param accountNumber 조회할 계좌번호
     * @return 잔액
     */
    private int getBalance(String accountNumber) {
        ApiResponse<BalanceResponse> response = getBalanceResponse(accountNumber);
        checkStatusIsOk(response.getStatus());
        return response.getData().getBalance();
    }

    /**
     * 신한은행 계좌 잔액 조회 API 호출 및 결과 반환
     *
     * @param accountNumber 조회할 계좌번호
     * @return API 응답
     */
    private ApiResponse<BalanceResponse> getBalanceResponse(String accountNumber) {
        return shinHanApiClient.getAccountBalance(createBalanceRequest(accountNumber));
    }

    /**
     * 신한은행 계좌 잔액 조회 API 요청 객체 생성
     *
     * @param accountNumber 계좌번호
     * @return 요청 객체
     */
    private BalanceRequest createBalanceRequest(String accountNumber) {
        return BalanceRequest.builder()
                .accountNumber(accountNumber)
                .build();
    }

    /**
     * 신한은행 계좌 잔액 조회 API 응답 status 확인
     *
     * @param status 응답 status
     * @throws NoSuchElementException OK 가 아닐경우
     */
    private void checkStatusIsOk(HttpStatus status) {
        log.debug("status={}", status);
        if (!isOk(status)) {
            throw new NoSuchElementException("잔액 조회에 실패하였습니다.");
        }
    }

    /**
     * HttpStatus.OK 여부 확인
     *
     * @param status HttpStatus 값
     * @return true: OK 일 경우. false: 다른 값일 경우
     */
    private boolean isOk(HttpStatus status) {
        return status.equals(HttpStatus.OK);
    }
}
