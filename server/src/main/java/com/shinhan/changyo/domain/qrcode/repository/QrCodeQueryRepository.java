package com.shinhan.changyo.domain.qrcode.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.shinhan.changyo.domain.account.QAccount.account;
import static com.shinhan.changyo.domain.qrcode.QQrCode.qrCode;

@Repository
public class QrCodeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public QrCodeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<QrCodeResponse> getQrCodes(String loginId) {
        return queryFactory
                .select(Projections.constructor(QrCodeResponse.class,
                        qrCode.qrCodeId,
                        qrCode.title,
                        qrCode.account.accountNumber,
                        qrCode.amount
                        ))
                .from(qrCode)
                .join(qrCode.account, account)
                .where(account.member.loginId.eq(loginId))
                .fetch();
    }
}
