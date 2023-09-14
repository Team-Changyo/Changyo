package com.shinhan.changyo.domain.transfer;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.controller.transfer.response.ClientAccountResponse;
import com.shinhan.changyo.api.controller.transfer.response.SimpleStoreAccountResponse;
import com.shinhan.changyo.api.controller.transfer.response.StoreAccountResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.shinhan.changyo.domain.account.QAccount.account;
import static com.shinhan.changyo.domain.member.QMember.member;
import static com.shinhan.changyo.domain.qrcode.QQrCode.qrCode;
import static com.shinhan.changyo.domain.qrcode.QSimpleQrCode.simpleQrCode;

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
                        account.id,
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
    public StoreAccountResponse getStoreAccountByQrCodeId(Long qrCodeId) {
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

    /**
     * 간편송금 이체정보 조회
     *
     * @param simpleQrCodeId 간편 송금 QR 코드 식별키
     * @return 간편송금 이체정보
     */
    public SimpleStoreAccountResponse getStoreAccountBySimpleQrCodeId(Long simpleQrCodeId) {
        return queryFactory
                .select(Projections.constructor(SimpleStoreAccountResponse.class,
                        simpleQrCode.id,
                        simpleQrCode.memberName,
                        simpleQrCode.amount,
                        simpleQrCode.bankCode,
                        simpleQrCode.accountNumber
                ))
                .from(simpleQrCode)
                .where(simpleQrCode.id.eq(simpleQrCodeId))
                .fetchOne();
    }
}