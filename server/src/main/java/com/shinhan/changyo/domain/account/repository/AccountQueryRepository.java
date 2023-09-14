package com.shinhan.changyo.domain.account.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.controller.account.response.AccountDetailResponse;
import com.shinhan.changyo.domain.account.Account;
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
     * 회원별 계좌 전체 조회
     *
     * @param loginId 회원 로그인 아이디
     * @return 계좌 개수, 계좌 정보 목록
     */
    public List<AccountDetailResponse> getAccountsByMemberId(String loginId) {
        return queryFactory
                .select(Projections.constructor(AccountDetailResponse.class,
                        account.id,
                        account.accountNumber,
                        account.balance,
                        account.bankCode,
                        account.mainAccount,
                        account.title
                ))
                .from(account)
                .join(account.member, member)
                .where(account.member.loginId.eq(loginId),
                        account.active.eq(true))
                .fetch();
    }

    /**
     * 회원별 전체 계좌 수 조회
     *
     * @param memberId 조회할 회원 식별키
     * @return 해당 회원의 전체 계좌 개수
     */
    public Integer getAccountSizeByMemberId(Long memberId) {
        return queryFactory
                .select(account.count())
                .from(account)
                .join(account.member, member)
                .where(account.member.id.eq(memberId),
                        account.active.eq(true))
                .fetchFirst().intValue();
    }

    public List<Account> getAccountsByMainAccountOrId(Long accountId) {
        BooleanExpression condition = account.mainAccount.eq(true)
                .or(account.id.eq(accountId));

        return queryFactory
                .select(account)
                .from(account)
                .where(condition,
                        account.active.eq(true))
                .orderBy(account.mainAccount.desc())
                .fetch();
    }

    public Account getMainAccountsById(Long id) {
        return queryFactory
                .select(account)
                .from(account)
                .where(account.mainAccount.eq(true),
                        account.member.id.eq(id))
                .fetchOne();
    }
}
