package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.controller.trade.response.WithdrawalResponse;
import com.shinhan.changyo.domain.trade.repository.TradeQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 보증금 거래내역 조회 서비스
 *
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TradeQueryService {

    private final TradeQueryRepository tradeQueryRepository;

    /**
     * 송금내역 목록 조회
     *
     * @param memberId 조회할 회원 식별키
     * @return 해당 회원의 송금내역 목록
     */
    public WithdrawalResponse getWithdrawalTrades(Long memberId) {
        return null;
    }
}
