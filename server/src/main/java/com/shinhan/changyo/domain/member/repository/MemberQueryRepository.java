package com.shinhan.changyo.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.domain.member.QMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.shinhan.changyo.domain.member.QMember.member;

/**
 * 회원 쿼리용 레포지토리
 *
 * @author 최영환
 */
@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 로그인 아이디 중복 체크
     *
     * @param loginId 중복 체크 할 대상 로그인 아이디
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean existLoginId(String loginId) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.loginId.eq(loginId))
                .fetchFirst();

        return result != null;
    }
}
