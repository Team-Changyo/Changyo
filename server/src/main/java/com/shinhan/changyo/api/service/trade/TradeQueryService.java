package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.controller.trade.response.DepositDetailResponse;
import com.shinhan.changyo.api.controller.trade.response.DepositResponse;
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
     * 보증금 송금내역 목록 조회
     *
     * @param memberId 조회할 회원 식별키
     * @return 해당 회원의 보증금 송금내역 목록
     */
    public WithdrawalResponse getWithdrawalTrades(Long memberId) {
        return null;
    }

    /**
     * 보증금 입금내역 목록 조회
     *
     * @param memberId 조회할 회원 식별키
     * @return 해당 회원의 보증금 입금내역 목록
     */
    public DepositResponse getDepositTrades(Long memberId) {
        return null;
    }

    /**
     * 보증금 정산관리 상세조회
     *
     * @param qrCodeId QR 코드 식별키
     * @param status   거래 상태
     * @return 보증금 정산관리 상세조회 목록
     */
    public DepositDetailResponse getDepositDetails(Long qrCodeId, String status) {
        return null;
    }
}
