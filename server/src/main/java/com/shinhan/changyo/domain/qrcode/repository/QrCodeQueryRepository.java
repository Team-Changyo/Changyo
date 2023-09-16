package com.shinhan.changyo.domain.qrcode.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeResponse;
import com.shinhan.changyo.api.service.trade.dto.QrCodeTradeDto;
import com.shinhan.changyo.domain.account.repository.AccountQueryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.shinhan.changyo.domain.account.QAccount.account;
import static com.shinhan.changyo.domain.qrcode.QQrCode.qrCode;
import static com.shinhan.changyo.domain.trade.SizeConstants.PAGE_SIZE;

@Repository
public class QrCodeQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final AccountQueryRepository accountQueryRepository;

    public QrCodeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.accountQueryRepository = new AccountQueryRepository(em);
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
    public QrCodeTradeDto getQrCodeTitleAndAmount(Long qrCodeId) {
        return queryFactory
                .select(Projections.constructor(QrCodeTradeDto.class,
                        qrCode.qrCodeId,
                        qrCode.title,
                        qrCode.amount
                ))
                .from(qrCode)
                .where(qrCode.qrCodeId.eq(qrCodeId))
                .fetchOne();
    }

    /**
     * 보증금 정산관리 조회
     *
     * @param loginId      로그인한 회원의 로그인 아이디
     * @param lastQrCodeId 마지막으로 조회된 QR 코드 식별키
     * @return 해당 회원의 보증금 입금내역 목록
     */
    public List<QrCodeTradeDto> getQrCodesByLoginId(String loginId, Long lastQrCodeId) {
        List<Long> accountIds = accountQueryRepository.getAccountIdsByLoginId(loginId);

        if (accountIds == null || accountIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> qrCodeIds = getQrCodeIds(accountIds);

        if (qrCodeIds == null || qrCodeIds.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
                .selectDistinct(Projections.constructor(QrCodeTradeDto.class,
                        qrCode.qrCodeId,
                        qrCode.title,
                        qrCode.amount
                ))
                .from(qrCode)
                .where(
                        qrCode.qrCodeId.in(qrCodeIds),
                        isLagerThanLastQrCodeId(lastQrCodeId)
                )
                .groupBy(qrCode.qrCodeId)
                .orderBy(qrCode.qrCodeId.desc())
                .limit(PAGE_SIZE + 1)
                .fetch();
    }

    /**
     * QR 코드 식별키 목록 조회
     *
     * @param accountIds 계좌 식별키 목록
     * @return 계좌들에 해당하는 QR 코드 식별키 목록
     */
    private List<Long> getQrCodeIds(List<Long> accountIds) {
        return queryFactory
                .select(qrCode.qrCodeId)
                .from(qrCode)
                .join(qrCode.account, account)
                .where(account.id.in(accountIds))
                .orderBy(qrCode.createdDate.desc())
                .fetch();
    }

    private BooleanExpression isLagerThanLastQrCodeId(Long qrCodeId) {
        return qrCodeId == null ? null : qrCode.qrCodeId.lt(qrCodeId);
    }

}
