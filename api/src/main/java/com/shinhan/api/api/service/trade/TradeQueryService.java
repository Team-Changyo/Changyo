package com.shinhan.api.api.service.trade;

import com.shinhan.api.api.controller.trade.response.TradeDetailResponse;
import com.shinhan.api.api.controller.trade.response.TradeResponse;
import com.shinhan.api.domain.account.Account;
import com.shinhan.api.domain.account.repository.AccountQueryRepository;
import com.shinhan.api.domain.trade.repository.TradeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeQueryService {

    private final AccountQueryRepository accountQueryRepository;
    private final TradeQueryRepository tradeQueryRepository;

    public TradeResponse getTrade(String accountNumber) {
        Account account = accountQueryRepository.getAccount(accountNumber);
        List<TradeDetailResponse> responses = tradeQueryRepository.getTradeHistory(accountNumber);
        return TradeResponse.of(account, responses);
    }
}
