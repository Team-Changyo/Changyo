package com.shinhan.api.domain.account.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.api.controller.account.response.CustomerNameResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.shinhan.api.domain.account.QAccount.*;

@Repository
public class AccountQueryRepository {

    private final JPAQueryFactory queryFactory;

    public AccountQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public AccountResponse getAccountBalance(String accountNumber) {
        return queryFactory
            .select(Projections.constructor(AccountResponse.class,
                Expressions.asString(accountNumber),
                account.balance
            ))
            .from(account)
            .where(account.accountNumber.eq(accountNumber))
            .fetchOne();
    }

    public CustomerNameResponse getCustomerName(String bankCode, String accountNumber) {
        return queryFactory
            .select(Projections.constructor(CustomerNameResponse.class,
                Expressions.asString(bankCode),
                Expressions.asString(accountNumber),
                account.customerName
            ))
            .from(account)
            .where(
                account.bankCode.eq(bankCode),
                account.accountNumber.eq(accountNumber)
            )
            .fetchOne();
    }
}
