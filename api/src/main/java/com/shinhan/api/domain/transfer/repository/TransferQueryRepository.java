package com.shinhan.api.domain.transfer.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.api.api.controller.trade.response.TradeDetailResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.shinhan.api.domain.account.QAccount.account;
import static com.shinhan.api.domain.trade.QTrade.trade;

@Repository
public class TransferQueryRepository {

    private final JPAQueryFactory queryFactory;

    public TransferQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}