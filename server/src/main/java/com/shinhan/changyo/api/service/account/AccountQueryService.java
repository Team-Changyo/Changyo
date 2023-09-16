package com.shinhan.changyo.api.service.account;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.account.response.AccountDetailResponse;
import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.api.controller.account.response.AccountTradeAllResponse;
import com.shinhan.changyo.api.controller.account.response.AllTradeResponse;
import com.shinhan.changyo.api.service.account.dto.AccountDto;
import com.shinhan.changyo.api.service.util.exception.ForbiddenException;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.client.request.TradeRequest;
import com.shinhan.changyo.client.response.TradeDetailResponse;
import com.shinhan.changyo.client.response.TradeResponse;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    private final AccountRepository accountRepository;
    private final ShinHanApiClient shinHanApiClient;

    /**
     * 회원별 계좌 전체 조회
     *
     * @param loginId 계좌 조회할 회원 식별키
     * @return 계좌 개수, 계좌 정보 목록
     */
    public AccountResponse getAccounts(String loginId) {
        List<AccountDetailResponse> accounts = accountQueryRepository.getAccountsByMemberId(loginId);

        int totalBalance = accounts.stream()
                .mapToInt(AccountDetailResponse::getBalance)
                .sum();

        List<String> bankCodeList = accounts.stream()
                .map(AccountDetailResponse::getBankCode)
                .distinct()
                .collect(Collectors.toList());

        return AccountResponse.of(accounts, totalBalance, bankCodeList, accounts.size());
    }

    /**
     * 입지 구분별 계좌내역 조회
     *
     * @param dto    조화할 계좌 정보
     * @param status 입지구분 (0: 전체, 1: 입금, 2: 출금)
     * @return 입지 구분별 계좌내역
     */
    public AccountTradeAllResponse getAccountTradeAll(AccountDto dto, int status) {
        // 계좌 조회
        Account findAccount = getAccount(dto.getAccountId());
        // 접근 권한 체크
        checkAccessibility(dto.getLoginId(), findAccount);
        // 신한 api 호출
        List<TradeDetailResponse> trades = getTradeDetailResponses(findAccount);

        // 거래 일자별로 정렬된 Map(Key: 거래일자 value: 해당 일자의 거래내역)
        Map<String, List<AllTradeResponse>> sortedAllTradeResponses = getSortedAllTradeResponses(status, trades);

        return AccountTradeAllResponse.builder()
                .accountId(dto.getAccountId())
                .accountNumber(findAccount.getAccountNumber())
                .balance(findAccount.getBalance())
                .bankCode(findAccount.getBankCode())
                .title(findAccount.getTitle())
                .allTradeResponses(sortedAllTradeResponses)
                .build();
    }

    private Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() ->
                new NoSuchElementException("존재 하지 않는 계좌입니다."));
    }

    private void checkAccessibility(String loginId, Account findAccount) {
        if (!findAccount.getMember().getLoginId().equals(loginId)) {
            throw new ForbiddenException("접근 권한이 없습니다.");
        }
    }

    private List<TradeDetailResponse> getTradeDetailResponses(Account findAccount) {
        ApiResponse<TradeResponse> tradeResponse =
                shinHanApiClient.trade(createTradeRequest(findAccount.getAccountNumber()));

        return tradeResponse.getData().getTrades();
    }

    private TradeRequest createTradeRequest(String accountNumber) {
        return TradeRequest.builder()
                .accountNumber(accountNumber)
                .build();
    }

    private Map<String, List<AllTradeResponse>> getSortedAllTradeResponses(int status, List<TradeDetailResponse> trades) {
        Map<String, List<AllTradeResponse>> sortedAllTradeResponses = new LinkedHashMap<>();
        if (!trades.isEmpty()) {
            Map<String, List<AllTradeResponse>> allTradeResponses = groupByTradeDate(trades, status);
            sortedAllTradeResponses = sortMapOrderByKeyDesc(allTradeResponses);
        }
        return sortedAllTradeResponses;
    }

    /**
     * 신한 API 응답 전처리 후 Map 생성
     *+
     * @param trades
     * @param status
     * @return
     */
    private Map<String, List<AllTradeResponse>> groupByTradeDate(List<TradeDetailResponse> trades, int status) {

        // 필요한 정보만 뽑아서 list 화
        List<AllTradeResponse> allTradeResponses = trades.stream()
                // 입지 구분에 따라서 처리
                .filter(trade -> {
                    if (status == 0) {
                        return true;
                    } else if (status == 1) {
                        return trade.getStatus() == 1;
                    } else if (status == 2) {
                        return trade.getStatus() == 2;
                    } else {
                        return false;
                    }
                })
                .map(trade -> AllTradeResponse.builder()
                        .tradeDate(trade.getTradeDate())
                        .tradeTime(trade.getTradeTime())
                        .content(trade.getContent())
                        .balance(trade.getBalance())
                        .withdrawalAmount(trade.getWithdrawalAmount())
                        .depositAmount(trade.getDepositAmount())
                        .status(trade.getStatus())
                        .build())
                .collect(Collectors.toList());

        // 거래날짜별로 묶기
        return allTradeResponses.stream().collect(Collectors.groupingBy(
                AllTradeResponse::getTradeDate
        ));
    }


    /**
     * 생성된 Map 정렬
     *
     * @param unsortedMap
     * @return
     */
    public Map<String, List<AllTradeResponse>> sortMapOrderByKeyDesc(Map<String, List<AllTradeResponse>> unsortedMap) {
        return unsortedMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey())) // 키를 내림차순으로 정렬
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
