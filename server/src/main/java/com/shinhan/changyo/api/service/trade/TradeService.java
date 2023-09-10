package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
import com.shinhan.changyo.api.service.trade.dto.ReturnDepositDto;
import com.shinhan.changyo.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 보증금 거래내역 서비스
 * 
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    /**
     * 보증금 송금 서비스
     *
     * @param dto 보증금 송금 정보
     * @return 보증금 거래내역 식별키
     */
    public Long createTrade(CreateTradeDto dto) {
        // 신한 잔액 조회 호출해서 예외처리 먼저
        // 출금 계좌에서 돈빼고 거래내역 생성
        // 신한 이체 API 호출
        return null;
    }

    public Boolean returnDeposit(List<ReturnDepositDto> dtos) {
        return false;
    }
}
