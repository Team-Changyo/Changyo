package com.shinhan.changyo.api.service.account;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.controller.account.response.AccountDetailResponse;
import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.api.controller.account.response.AccountTradeAllResponse;
import com.shinhan.changyo.api.controller.account.response.AllTradeResponse;
import com.shinhan.changyo.api.service.account.exception.NoAccountException;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.client.request.TradeRequest;
import com.shinhan.changyo.client.response.TradeDetailResponse;
import com.shinhan.changyo.client.response.TradeResponse;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import com.shinhan.changyo.domain.trade.Trade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
        checkIsEmpty(accounts);
        return AccountResponse.of(accounts, accounts.size());
    }

    /**
     * 등록 계좌 존재 여부 확인
     *
     * @param accounts 등록된 계좌 목록
     * @throws EntityNotFoundException 등록된 계좌 목록이 비어있거나 NULL 일 경우
     */
    private void checkIsEmpty(List<AccountDetailResponse> accounts) {
        if (accounts == null || accounts.isEmpty()) {
            throw new NoAccountException("등록된 계좌가 없습니다.");
        }
    }

    public AccountTradeAllResponse getAccountTradeAll(String loginId, Long accountId)  {
        // 계좌 조회
        Account findAccount = accountRepository.findById(accountId).orElseThrow(() ->
                new NoSuchElementException("존재 하지 않는 계좌입니다."));

        // 접근 권한 체크
        if(!findAccount.getMember().getLoginId().equals(loginId)){
            throw new SecurityException("접근 권한이 없습니다.");
        }

        // 신한 api 호출
        ApiResponse<TradeResponse> tradeResponse =
                shinHanApiClient.trade(createTradeRequest(findAccount.getAccountNumber()));

        // 데이터 뽑기
        List<TradeDetailResponse> trades = tradeResponse.getData().getTrades();

        // 거래내역이 없는 경우
        if(trades.size() == 0){
            throw new NoAccountException("계좌 거래 내역이 존재하지 않습니다.");
        }

        // 거래 일자 별로 묶어서 리스트화
        Map<String, List<AllTradeResponse>> allTradeResponses = createGroupByDateTime(trades);


        AccountTradeAllResponse response = AccountTradeAllResponse.builder()
                .accountId(accountId)
                .accountNumber(findAccount.getAccountNumber())
                .balance(findAccount.getBalance())
                .bankCode(findAccount.getBankCode())
                .allTradeResponses(allTradeResponses)
                .build();

        return response;
    }

    private TradeRequest createTradeRequest(String accountNumber){
        return TradeRequest.builder()
                .accountNumber(accountNumber)
                .build();
    }


    private Map<String, List<AllTradeResponse>> createGroupByDateTime(List<TradeDetailResponse> trades){

        // 필요한 정보만 뽑아서 list 화
        List<AllTradeResponse> allTradeResponses = trades.stream()
                .map(trade-> {
                    AllTradeResponse tmp = AllTradeResponse.builder()
                            .tradeDate(trade.getTradeDate())
                            .tradeTime(trade.getTradeTime())
                            .content(trade.getContent())
                            .balance(trade.getBalance())
                            .withdrawalAmount(trade.getWithdrawalAmount())
                            .depositAmount(trade.getDepositAmount())
                            .build();
                    return tmp;

                })
                .collect(Collectors.toList());

        // 거래날짜별로 묶기
        Map<String, List<AllTradeResponse>> res =
                allTradeResponses.stream().collect(Collectors.groupingBy(
                        AllTradeResponse::getTradeDate
                ));


        return res;
    }

}
