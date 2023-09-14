package com.shinhan.changyo.domain.qrcode.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeResponse;
import com.shinhan.changyo.api.service.trade.dto.QRCodeTradeDto;
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
                        qrCode.account.bankCode,
                        qrCode.amount
                ))
                .from(qrCode)
                .join(qrCode.account, account)
                .where(account.member.loginId.eq(loginId))
                .fetch();
    }

    /**
     * QR Code 이름, 입금단위 조회
     *
     * @param qrCodeId QR Code 식별키
     * @return 이름, 입금단위 객체
     * @author 최영환
     */
    public QRCodeTradeDto getQrCodeTitleAndAmount(Long qrCodeId) {
        return queryFactory
                .select(Projections.constructor(QRCodeTradeDto.class,
                        qrCode.title,
                        qrCode.amount
                ))
                .from(qrCode)
                .where(qrCode.qrCodeId.eq(qrCodeId))
                .fetchOne();
    }

    /**
     * 마지막으로 저장된 QR 코드 식별키 조회
     * 
     * @return 마지막으로 저장된 QR 코드 식별키
     */
    public Long getLastSavedQrCodeId() {
        return queryFactory
                .select(qrCode.qrCodeId)
                .from(qrCode)
                .orderBy(qrCode.qrCodeId.desc())
                .fetchFirst();
    }
}
