package com.shinhan.changyo.domain.trade.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.controller.trade.response.DepositDetailDto;
import com.shinhan.changyo.api.controller.trade.response.DepositOverviewResponse;
import com.shinhan.changyo.api.controller.trade.response.WithdrawalDetailResponse;
import com.shinhan.changyo.api.service.trade.dto.MemberAccountDto;
import com.shinhan.changyo.domain.trade.TradeStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.shinhan.changyo.domain.account.QAccount.account;
import static com.shinhan.changyo.domain.member.QMember.member;
import static com.shinhan.changyo.domain.qrcode.QQrCode.qrCode;
import static com.shinhan.changyo.domain.trade.QTrade.trade;

/**
 * 보증금 거래내역 쿼리 저장소
 *
 * @author 최영환
 */
@Repository
public class TradeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public TradeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 보증금 송금내역 조회
     *
     * @param loginId 조회하려는 회원의 로그인 아이디
     * @return 해당 회원의 전체 보증금 송금내역
     */
    public List<WithdrawalDetailResponse> getWithdrawalTrades(String loginId) {
        List<Long> accountIds = getAccountIdsByLoginId(loginId);

        if (accountIds == null || accountIds.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(WithdrawalDetailResponse.class,
                        trade.id,
                        qrCode.title,
                        member.name,
                        trade.withdrawalAmount,
                        trade.status,
                        trade.createdDate
                ))
                .from(trade)
                .join(trade.account, account)
                .join(trade.qrCode, qrCode)
                .join(qrCode.account, account)
                .join(account.member, member)
                .where(account.id.in(accountIds))
                .orderBy(trade.createdDate.desc())
                .fetch();
    }

    /**
     * 보증금 정산관리 조회
     *
     * @param loginId 로그인한 회원의 로그인 아이디
     * @return 해당 회원의 보증금 입금내역 목록
     */
    public List<DepositOverviewResponse> getDepositTrades(String loginId) {
        List<Long> accountIds = getAccountIdsByLoginId(loginId);

        if (accountIds == null || accountIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> qrCodeIds = getQrCodeIds(accountIds);

        if (qrCodeIds == null || qrCodeIds.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(DepositOverviewResponse.class,
                        qrCode.qrCodeId,
                        qrCode.title,
                        trade.depositAmount,
                        new CaseBuilder().when(trade.status.eq(TradeStatus.WAIT)).then(trade.depositAmount).otherwise(0).sum(),
                        new CaseBuilder().when(trade.status.eq(TradeStatus.WAIT)).then(trade.depositAmount).otherwise(0).count().intValue()
                ))
                .from(trade)
                .join(trade.qrCode, qrCode)
                .where(qrCode.qrCodeId.in(qrCodeIds))
                .groupBy(qrCode.qrCodeId)
                .orderBy(trade.createdDate.desc())
                .fetch();
    }

    /**
     * 계좌 식별키 목록 조회
     *
     * @param loginId 조회할 회원 로그인 아이디
     * @return 해당 회원이 가진 계좌 식별키 목록
     */
    private List<Long> getAccountIdsByLoginId(String loginId) {
        return queryFactory.select(account.id)
                .from(account)
                .join(account.member, member)
                .where(member.loginId.eq(loginId))
                .fetch();
    }

    /**
     * QR 코드 식별키 목록 조회
     *
     * @param accountIds 계좌 식별키 목록
     * @return 계좌들에 해당하는 QR 코드 식별키 목록
     */
    private List<Long> getQrCodeIds(List<Long> accountIds) {
        return queryFactory
                .select(qrCode.qrCodeId)
                .from(qrCode)
                .join(qrCode.account, account)
                .where(account.id.in(accountIds))
                .fetch();
    }

    public List<DepositDetailDto> getDepositDetails(Long qrCodeId) {
        return queryFactory
                .select(Projections.constructor(DepositDetailDto.class,
                        trade.id,
                        trade.status,
                        account.member.name,
                        trade.createdDate
                ))
                .from(trade)
                .join(trade.account, account)
                .join(trade.account.member, member)
                .where(qrCode.qrCodeId.eq(qrCodeId))
                .orderBy(trade.createdDate.desc())
                .fetch();
    }

    /**
     * 입금자 (QR 코드 소유 회원) 계좌 정보 조회
     *
     * @param tradeId 보증금 거래내역 식별키
     * @return 입금자 (QR 코드 소유 회원) 계좌 정보
     */
    public MemberAccountDto getDepositAccount(Long tradeId) {
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
    public MemberAccountDto getWithdrawalAccount(Long tradeId) {
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
