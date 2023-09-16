package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.controller.trade.response.*;
import com.shinhan.changyo.api.service.trade.dto.DepositDetailDto;
import com.shinhan.changyo.api.service.trade.dto.QrCodeTradeDto;
import com.shinhan.changyo.domain.qrcode.repository.QrCodeQueryRepository;
import com.shinhan.changyo.domain.trade.TradeStatus;
import com.shinhan.changyo.domain.trade.repository.TradeQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.shinhan.changyo.domain.trade.SizeConstants.PAGE_SIZE;

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

    private final QrCodeQueryRepository qrCodeQueryRepository;

    /**
     * 보증금 송금관리 반환대기 내역 조회
     *
     * @param loginId 로그인한 회원 아이디
     * @return 해당 회원의 반환대기 중인 보증금 송금 거래내역 목록
     */
    public WaitWithdrawalResponse getWaitingWithdrawalTrades(String loginId) {
        List<WaitWithdrawalDetailResponse> withdrawals = tradeQueryRepository.getWaitingWithdrawalTrades(loginId);
        log.debug("withdrawals={}", withdrawals);

        return WaitWithdrawalResponse.of(withdrawals.size(), withdrawals);
    }


    /**
     * 보증금 송금관리 반환완료 내역 조회
     *
     * @param loginId     로그인한 회원 아이디
     * @param lastTradeId 마지막으로 조회된 거래내역 식별키
     * @return 해당 회원의 반환완료된 보증금 송금 거래내역 목록
     */
    public DoneWithdrawalResponse getDoneWithdrawalTrades(String loginId, Long lastTradeId) {
        int totalCount = tradeQueryRepository.getDoneWithdrawalTradesCount(loginId).intValue();

        List<DoneWithdrawalDetailResponse> doneWithdrawals = tradeQueryRepository.getDoneWithdrawalTrades(loginId, lastTradeId);
        log.debug("doneWithdrawals={}", doneWithdrawals);

        Boolean hasNextPage = checkHasNextPage(doneWithdrawals);

        return DoneWithdrawalResponse.of(hasNextPage, totalCount, doneWithdrawals);
    }

    /**
     * 보증금 정산관리 조회
     *
     * @param loginId      로그인한 회원의 로그인 아이디
     * @param lastQrCodeId 마지막으로 조회된 QR 코드 식별키
     * @return 해당 회원의 보증금 입금내역 목록
     */
    public DepositResponse getDepositTrades(String loginId, Long lastQrCodeId) {
        int totalCount = tradeQueryRepository.getDepositTradesTotalCount(loginId);
        List<QrCodeTradeDto> qrCodeTrades = qrCodeQueryRepository.getQrCodesByLoginId(loginId, lastQrCodeId);
        log.debug("qrCodeTrades={}", qrCodeTrades);

        List<DepositOverviewResponse> overviews = qrCodeTrades.stream().map(qrCodeTrade -> DepositOverviewResponse.builder()
                        .qrCodeId(qrCodeTrade.getQrCodeId())
                        .qrCodeTitle(qrCodeTrade.getTitle())
                        .amount(qrCodeTrade.getAmount())
                        .remainCount(getWaitDepositCountByQrCodeId(qrCodeTrade.getQrCodeId()))
                        .remainTotal(getWaitTotalAmountByQrCodeId(qrCodeTrade.getQrCodeId()))
                        .build()
                )
                .collect(Collectors.toList());

        boolean hasNextPage = checkHasNextPage(qrCodeTrades);

        return DepositResponse.of(hasNextPage, totalCount, overviews);
    }

    /**
     * 보증금 정산관리 상세조회
     *
     * @param qrCodeId    QR 코드 식별키
     * @param lastTradeId 마지막
     * @return 보증금 정산관리 상세조회 목록
     */
    public DepositDetailResponse getDepositDetails(Long qrCodeId, Long lastTradeId) {
        QrCodeTradeDto qrCodeTrade = getQrCodeTrade(qrCodeId);

        int waitCount = getWaitDepositCountByQrCodeId(qrCodeId);
        int doneCount = getDoneDepositCountByQrCodeId(qrCodeId);
        int totalAmount = getTotalAmountByQrCodeId(qrCodeId);

        List<DepositDetailDto> deposits = tradeQueryRepository.getDepositDetails(qrCodeId, lastTradeId);
        log.debug("deposits={}", deposits);

        return createDepositDetailResponse(qrCodeTrade, deposits, waitCount, doneCount, totalAmount);
    }

    /**
     * QR 코드별 반환대기 상태 총 개수 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return QR 코드별 반환대기 총 개수
     */
    private int getWaitDepositCountByQrCodeId(Long qrCodeId) {
        Long result = tradeQueryRepository.getWaitDepositCountByQrCodeId(qrCodeId);
        return castToIntOrDefault(result);
    }

    /**
     * QR 코드별 반환완료 상태 총 개수 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return QR 코드별 반환완료 총 개수
     */
    private int getDoneDepositCountByQrCodeId(Long qrCodeId) {
        Long result = tradeQueryRepository.getDoneDepositCountByQrCodeId(qrCodeId);
        return castToIntOrDefault(result);
    }

    /**
     * QR 코드별 총 금액 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return 해당 QR 코드 총 금액
     */
    private int getTotalAmountByQrCodeId(Long qrCodeId) {
        Integer result = tradeQueryRepository.getTotalAmountByQrCodeId(qrCodeId);
        return getResultOrZero(result);
    }

    /**
     * QR 코드별 반환대기 상태 총 금액 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return QR 코드별 반환대기 총 금액
     */
    private int getWaitTotalAmountByQrCodeId(Long qrCodeId) {
        Integer result = tradeQueryRepository.getWaitTotalAmountByQrCodeId(qrCodeId);
        return getResultOrZero(result);
    }

    /**
     * QR 코드 이름, 거래 금액 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return QR 코드 이름, 거래 금액
     */
    private QrCodeTradeDto getQrCodeTrade(Long qrCodeId) {
        QrCodeTradeDto qrCodeTrade = qrCodeQueryRepository.getQrCodeTitleAndAmount(qrCodeId);
        if (qrCodeTrade == null) {
            throw new NoSuchElementException("존재하지 않는 보증금 정산내역입니다.");
        }
        return qrCodeTrade;
    }

    /**
     * 보증금 정산관리 상세조회 목록 생성
     *
     * @param qrCodeTrade QR 코드 제목, 입금단위 DTO
     * @param deposits    보증금 입금내역
     * @param waitCount   반환대기 목록 전체개수
     * @param doneCount   반환완료 목록 전체개수
     * @param totalAmount 총 금액
     * @return 보증금 정산관리 상세조회 목록
     */
    private DepositDetailResponse createDepositDetailResponse(QrCodeTradeDto qrCodeTrade, List<DepositDetailDto> deposits, int waitCount, int doneCount, int totalAmount) {
        List<DepositDetailDto> waitDetails = filterWaitDepositDetails(deposits);
        List<DepositDetailDto> doneDetails = filterDoneDepositDetails(deposits);
        boolean hasNextPage = checkHasNextPage(deposits);

        return DepositDetailResponse.builder()
                .hasNextPage(hasNextPage)
                .qrCodeTitle(qrCodeTrade.getTitle())
                .amount(qrCodeTrade.getAmount())
                .totalAmount(totalAmount)
                .waitCount(waitCount)
                .doneCount(doneCount)
                .waitDetails(waitDetails)
                .doneDetails(doneDetails)
                .build();
    }

    /**
     * 반환대기 입금내역 필터링
     *
     * @param deposits 입금내역
     * @return 반환대기 입금내역 목록
     */
    private List<DepositDetailDto> filterDoneDepositDetails(List<DepositDetailDto> deposits) {
        return deposits.stream()
                .filter(detail -> !detail.getStatus().equals(TradeStatus.WAIT))
                .collect(Collectors.toList());
    }

    /**
     * 반환완료 입금내역 필터링
     *
     * @param details 입금내역
     * @return 반환완료 입금내역 목록
     */
    private List<DepositDetailDto> filterWaitDepositDetails(List<DepositDetailDto> details) {
        return details.stream()
                .filter(detail -> detail.getStatus().equals(TradeStatus.WAIT))
                .collect(Collectors.toList());
    }

    /**
     * NULL 체크 후 값 반환
     *
     * @param intValue 체크할 Integer 타입 정수갑
     * @return null 인 경우 0, 아닌 경우 원본값
     */
    private int getResultOrZero(Integer intValue) {
        return intValue == null ? 0 : intValue;
    }

    /**
     * Long 타입 NULL 체크 후 Integer 변환
     *
     * @param longValue 체크할 Long 타입 정수갑
     * @return null 인 경우 0, 아닌 경우 원본값
     */
    private int castToIntOrDefault(Long longValue) {
        return longValue == null ? 0 : longValue.intValue();
    }

    /**
     * 다음페이지 존재여부 확인
     *
     * @param responses 조회된 응답객체 목록
     * @return true: 조회된 목록의 개수가 PAGE_SIZE 보다 큰 경우, false: 조회된 목록의 개수가 PAGE_SIZE 보다 작은 경우
     */
    private <T> boolean checkHasNextPage(List<T> responses) {
        if (responses.size() > PAGE_SIZE) {
            responses.remove(PAGE_SIZE);
            return true;
        }
        return false;
    }
}
