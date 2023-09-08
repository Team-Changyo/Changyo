package com.shinhan.changyo.domain.account.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * 계좌 쿼리 저장소
 * 
 * @author 최영환
 */
@Repository
public class AccountQueryRepository {

    private final JPAQueryFactory queryFactory;

    public AccountQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
