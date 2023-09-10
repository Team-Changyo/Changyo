package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.controller.trade.response.DepositDetailResponse;
import com.shinhan.changyo.api.controller.trade.response.DepositResponse;
import com.shinhan.changyo.api.controller.trade.response.WithdrawalDetailResponse;
import com.shinhan.changyo.api.controller.trade.response.WithdrawalResponse;
import com.shinhan.changyo.domain.trade.TradeStatus;
import com.shinhan.changyo.domain.trade.repository.TradeQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
     * 보증금 송금관리 조회
     *
     * @param loginId 로그인한 회원 아이디
     * @return 해당 회원의 보증금 송금 거래내역 목록
     */
    public WithdrawalResponse getWithdrawalTrades(String loginId) {
        log.debug("loginId={}", loginId);
        List<WithdrawalDetailResponse> withdrawals = tradeQueryRepository.getWithdrawalTrades(loginId);
        log.debug("withdrawals={}", withdrawals);

        List<WithdrawalDetailResponse> waitWithdrawals = filterWaitWithdrawals(withdrawals);
        log.debug("waitWithdrawals={}", waitWithdrawals);

        List<WithdrawalDetailResponse> doneWithdrawals = filterDoneWithdrawals(withdrawals);
        log.debug("doneWithdrawals={}", doneWithdrawals);

        return WithdrawalResponse.of(waitWithdrawals, doneWithdrawals);
    }

    /**
     * 반환대기 송금내역 필터링
     *
     * @param withdrawals 송금내역
     * @return 반환대기 송금내역
     */
    private List<WithdrawalDetailResponse> filterWaitWithdrawals(List<WithdrawalDetailResponse> withdrawals) {
        return withdrawals.stream()
                .filter(detail -> detail.getStatus().equals(TradeStatus.WAIT))
                .collect(Collectors.toList());
    }

    /**
     * 반환완료 송금내역 필터링
     *
     * @param withdrawals 송금내역
     * @return 반환완료 송금내역
     */
    private List<WithdrawalDetailResponse> filterDoneWithdrawals(List<WithdrawalDetailResponse> withdrawals) {
        return withdrawals.stream()
                .filter(detail -> !detail.getStatus().equals(TradeStatus.WAIT))
                .collect(Collectors.toList());
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
