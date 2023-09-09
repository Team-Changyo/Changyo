package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
import com.shinhan.changyo.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }
}
