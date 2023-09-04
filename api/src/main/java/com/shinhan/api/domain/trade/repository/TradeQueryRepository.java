package com.shinhan.api.domain.trade.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.api.api.controller.trade.response.TradeDetailResponse;
import com.shinhan.api.domain.account.QAccount;
import com.shinhan.api.domain.trade.QTrade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.shinhan.api.domain.account.QAccount.*;
import static com.shinhan.api.domain.trade.QTrade.*;

@Repository
public class TradeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public TradeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<TradeDetailResponse> getTradeHistory(String accountNumber) {
        return queryFactory
                .select(Projections.constructor(TradeDetailResponse.class,
                        trade.tradeDate,
                        trade.tradeTime,
                        trade.summary,
                        trade.withdrawalAmount,
                        trade.depositAmount,
                        trade.content,
                        trade.balance,
                        trade.status,
                        trade.dealershipName
                ))
                .from(trade)
                .join(trade.account, account)
                .where(trade.account.accountNumber.eq(accountNumber))
                .orderBy(trade.createdDate.desc())
                .fetch();
    }
}
