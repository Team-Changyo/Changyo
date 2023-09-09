package com.shinhan.changyo.domain.trade.repository;

import com.shinhan.changyo.domain.trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 보증금 거래내역 레포지토리
 * 
 * @author 최영환
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
}
