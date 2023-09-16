package com.shinhan.changyo.domain.trade.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.controller.trade.response.DoneWithdrawalDetailResponse;
import com.shinhan.changyo.api.controller.trade.response.WaitWithdrawalDetailResponse;
import com.shinhan.changyo.api.service.trade.dto.DepositDetailDto;
import com.shinhan.changyo.api.service.trade.dto.MemberAccountDto;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import com.shinhan.changyo.domain.trade.TradeStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.shinhan.changyo.domain.account.QAccount.account;
import static com.shinhan.changyo.domain.member.QMember.member;
import static com.shinhan.changyo.domain.qrcode.QQrCode.qrCode;
import static com.shinhan.changyo.domain.trade.QTrade.trade;
import static com.shinhan.changyo.domain.trade.SizeConstants.PAGE_SIZE;

/**
 * 보증금 거래내역 쿼리 저장소
 *
 * @author 최영환
 */
@Repository
public class TradeQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final AccountQueryRepository accountQueryRepository;

    public TradeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.accountQueryRepository = new AccountQueryRepository(em);
    }

    /**
     * 보증금 반환대기 송금내역 조회
     *
     * @param loginId 조회하려는 회원의 로그인 아이디
     * @return 해당 회원의 전체 보증금 반환대기 송금내역
     */
    public List<WaitWithdrawalDetailResponse> getWaitingWithdrawalTrades(String loginId) {
        List<Long> accountIds = accountQueryRepository.getAccountIdsByLoginId(loginId);

        if (accountIds == null || accountIds.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(WaitWithdrawalDetailResponse.class,
                        trade.id,
                        qrCode.title,
                        account.member.name,
                        trade.withdrawalAmount,
                        trade.status
                ))
                .from(trade)
                .join(trade.account, account)
                .join(account.member, member)
                .join(trade.qrCode, qrCode)
                .join(qrCode.account, account)
                .where(
                        trade.account.id.in(accountIds),
                        trade.status.eq(TradeStatus.WAIT)
                )
                .orderBy(trade.createdDate.desc())
                .fetch();
    }

    /**
     * 보증금 반환완료 송금내역 개수 조회
     *
     * @param loginId 현재 로그인한 회원의 로그인 아이디
     * @return 보증금 반환완료 송금내역 개수
     */
    public Long getDoneWithdrawalTradesCount(String loginId) {
        List<Long> accountIds = accountQueryRepository.getAccountIdsByLoginId(loginId);

        if (accountIds == null || accountIds.isEmpty()) {
            return 0L;
        }

        return queryFactory
                .select(trade.count())
                .from(trade)
                .join(trade.account, account)
                .join(trade.qrCode, qrCode)
                .join(qrCode.account, account)
                .join(account.member, member)
                .where(
                        trade.account.id.in(accountIds),
                        trade.status.ne(TradeStatus.WAIT)
                )
                .fetchOne();
    }

    /**
     * 보증금 반환완료 송금내역 조회
     *
     * @param loginId     현재 로그인한 회원의 로그인 아이디
     * @param lastTradeId 마지막으로 조회된 거래내역 식별키
     * @return 보증금 반환완료 송금내역
     */
    public List<DoneWithdrawalDetailResponse> getDoneWithdrawalTrades(String loginId, Long lastTradeId) {
        List<Long> accountIds = accountQueryRepository.getAccountIdsByLoginId(loginId);

        if (accountIds == null || accountIds.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(DoneWithdrawalDetailResponse.class,
                        trade.id,
                        qrCode.title,
                        account.member.name,
                        trade.withdrawalAmount,
                        trade.lastModifiedDate
                ))
                .from(trade)
                .join(trade.account, account)
                .join(account.member, member)
                .join(trade.qrCode, qrCode)
                .join(qrCode.account, account)
                .where(
                        trade.account.id.in(accountIds),
                        trade.status.ne(TradeStatus.WAIT),
                        isLagerThanLastTradeId(lastTradeId)
                )
                .orderBy(trade.createdDate.desc())
                .limit(PAGE_SIZE + 1)
                .fetch();
    }

    private BooleanExpression isLagerThanLastTradeId(Long tradeId) {
        return tradeId == null ? null : trade.id.lt(tradeId);
    }

    /**
     * QR 코드별 반환대기 상태 총 금액 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return QR 코드별 반환대기 상태 총 금액
     */
    public Integer getWaitTotalAmountByQrCodeId(Long qrCodeId) {
        return queryFactory
                .select(qrCode.amount.sum())
                .from(trade)
                .join(trade.qrCode, qrCode)
                .where(
                        qrCode.qrCodeId.eq(qrCodeId),
                        trade.status.eq(TradeStatus.WAIT)
                )
                .fetchOne();
    }

    /**
     * 보증금 정산관리 반환대기 목록개수 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return 보증금 정산대기 반환완료 목록개수
     */
    public Long getWaitDepositCountByQrCodeId(Long qrCodeId) {
        return queryFactory
                .select(trade.count())
                .from(trade)
                .join(trade.account, account)
                .join(account.member, member)
                .where(
                        trade.qrCode.qrCodeId.eq(qrCodeId),
                        trade.status.eq(TradeStatus.WAIT)
                )
                .fetchOne();
    }

    /**
     * 보증금 정산관리 반환완료 목록개수 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return 보증금 정산관리 반환완료 목록개수
     */
    public Long getDoneDepositCountByQrCodeId(Long qrCodeId) {
        return queryFactory
                .select(trade.count())
                .from(trade)
                .join(trade.account, account)
                .join(account.member, member)
                .where(
                        trade.qrCode.qrCodeId.eq(qrCodeId),
                        trade.status.ne(TradeStatus.WAIT)
                )
                .fetchOne();
    }

    /**
     * 보증금 정산관리 상세조회
     *
     * @param qrCodeId    QR 코드 식별키
     * @param lastTradeId 마지막으로 조회된 QR 코드 식별키
     * @return 보증금 정산관리 상세 내역
     */
    public List<DepositDetailDto> getDepositDetails(Long qrCodeId, Long lastTradeId) {
        return queryFactory
                .select(Projections.constructor(DepositDetailDto.class,
                        trade.id,
                        trade.status,
                        account.member.name,
                        trade.createdDate
                ))
                .from(trade)
                .join(trade.account, account)
                .join(account.member, member)
                .where(
                        trade.qrCode.qrCodeId.eq(qrCodeId),
                        isLagerThanLastTradeId(lastTradeId)
                )
                .orderBy(trade.createdDate.desc())
                .limit(PAGE_SIZE * 2 + 1)
                .fetch();
    }

    /**
     * QR 코드 식별키가 일치하는 모든 거래내역의 금액 총합 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return 거래내역 금액 총합
     */
    public Integer getTotalAmountByQrCodeId(Long qrCodeId) {
        return queryFactory
                .select(trade.depositAmount.sum())
                .from(trade)
                .where(trade.qrCode.qrCodeId.eq(qrCodeId))
                .fetchOne();
    }

    /**
     * 입금자 (QR 코드 소유 회원) 계좌 정보 조회
     *
     * @param tradeId 보증금 거래내역 식별키
     * @return 입금자 (QR 코드 소유 회원) 계좌 정보
     */
    public MemberAccountDto getDepositAccountByTradeId(Long tradeId) {
        return queryFactory
                .select(Projections.constructor(MemberAccountDto.class,
                        account.id,
                        account.accountNumber,
                        member.name
                ))
                .from(trade)
                .join(trade.qrCode, qrCode)
                .join(qrCode.account, account)
                .join(account.member, member)
                .where(trade.id.eq(tradeId))
                .fetchOne();
    }

    /**
     * 송금자 계좌 정보 조회
     *
     * @param tradeId 보증금 거래내역 식별키
     * @return 송금자 계좌 정보
     */
    public MemberAccountDto getWithdrawalAccountByTradeId(Long tradeId) {
        return queryFactory
                .select(Projections.constructor(MemberAccountDto.class,
                        account.id,
                        account.accountNumber,
                        member.name
                ))
                .from(trade)
                .join(trade.account, account)
                .join(account.member, member)
                .where(trade.id.eq(tradeId))
                .fetchOne();
    }
}
