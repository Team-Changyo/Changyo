package com.shinhan.changyo.domain.trade.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.controller.trade.response.WithdrawalDetailResponse;
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
        List<Long> accountIds = queryFactory.select(account.id)
                .from(account)
                .join(account.member, member)
                .where(member.loginId.eq(loginId))
                .fetch();

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
                .fetch();
    }
}
