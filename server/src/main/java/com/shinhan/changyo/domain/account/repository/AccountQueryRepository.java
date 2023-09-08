package com.shinhan.changyo.domain.account.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.controller.account.response.AccountDetailResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.shinhan.changyo.domain.account.QAccount.account;
import static com.shinhan.changyo.domain.member.QMember.member;

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

    /**
     * 계좌 전체 조회
     *
     * @param memberId 계좌 조회할 회원 식별키
     * @return 계좌 개수, 계좌 정보 목록
     */
    public List<AccountDetailResponse> getAccountsByMemberId(Long memberId) {
        return queryFactory
                .select(Projections.constructor(AccountDetailResponse.class,
                        account.accountNumber,
                        account.balance,
                        account.bankCode,
                        account.mainAccount
                ))
                .from(account)
                .join(account.member, member)
                .where(account.member.id.eq(memberId))
                .fetch();
    }

    public Integer getAccountSizeByMemberId(Long memberId) {
        return queryFactory
                .select(account.count())
                .from(account)
                .join(account.member, member)
                .where(account.member.id.eq(memberId))
                .fetchFirst().intValue();
    }
}
