package com.shinhan.changyo.domain.transfer;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.controller.transfer.response.ClientAccountResponse;
import com.shinhan.changyo.api.controller.transfer.response.StoreAccountResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.shinhan.changyo.domain.account.QAccount.account;
import static com.shinhan.changyo.domain.member.QMember.member;
import static com.shinhan.changyo.domain.qrcode.QQrCode.qrCode;

/**
 * 이체 정보 쿼리 저장소
 *
 * @author 최영환
 */
@Repository
public class TransferQueryRepository {
    private final JPAQueryFactory queryFactory;

    public TransferQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 출금(고객) 계좌 정보 조회
     *
     * @param loginId 현재 로그인한 회원의 로그인 아이디
     * @return 출금(고객) 계좌 정보
     */
    public ClientAccountResponse getClientAccount(String loginId) {
        return queryFactory
                .select(Projections.constructor(ClientAccountResponse.class,
                        account.bankCode,
                        account.accountNumber,
                        account.productName,
                        member.name,
                        account.balance
                ))
                .from(account)
                .join(account.member, member)
                .where(member.loginId.eq(loginId))
                .fetchOne();
    }

    /**
     * 입금(점주) 계좌 정보 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return 입금(점주) 계좌 정보
     */
    public StoreAccountResponse getStoreAccount(Long qrCodeId) {
        return queryFactory
                .select(Projections.constructor(StoreAccountResponse.class,
                        qrCode.qrCodeId,
                        qrCode.title,
                        qrCode.amount,
                        account.bankCode,
                        account.accountNumber,
                        account.productName,
                        member.name
                ))
                .from(qrCode)
                .join(qrCode.account, account)
                .join(account.member, member)
                .where(qrCode.qrCodeId.eq(qrCodeId))
                .fetchOne();
    }
}