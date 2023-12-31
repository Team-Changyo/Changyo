package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
import com.shinhan.changyo.api.service.trade.dto.MemberAccountDto;
import com.shinhan.changyo.api.service.trade.dto.ReturnDepositDto;
import com.shinhan.changyo.api.service.trade.dto.SimpleTradeDto;
import com.shinhan.changyo.api.service.util.exception.DuplicateException;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.client.request.TransferRequest;
import com.shinhan.changyo.client.response.TransferResponse;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.member.repository.MemberQueryRepository;
import com.shinhan.changyo.domain.qrcode.QrCode;
import com.shinhan.changyo.domain.qrcode.SimpleQrCode;
import com.shinhan.changyo.domain.qrcode.repository.QrCodeRepository;
import com.shinhan.changyo.domain.qrcode.repository.SimpleQrCodeRepository;
import com.shinhan.changyo.domain.report.Report;
import com.shinhan.changyo.domain.report.repository.ReportRepository;
import com.shinhan.changyo.domain.trade.Trade;
import com.shinhan.changyo.domain.trade.TradeStatus;
import com.shinhan.changyo.domain.trade.repository.TradeQueryRepository;
import com.shinhan.changyo.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 보증금 거래내역 서비스
 *
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TradeService {

    @Value("${changyo-account.accountNumber}")
    private String changyoAccountNumber;

    private final TradeRepository tradeRepository;
    private final ShinHanApiClient shinHanApiClient;
    private final AccountRepository accountRepository;
    private final QrCodeRepository qrCodeRepository;
    private final TradeQueryRepository tradeQueryRepository;
    private final ReportRepository reportRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final SimpleQrCodeRepository simpleQrCodeRepository;

    /**
     * 보증금 송금
     *
     * @param dto     보증금 송금 정보
     * @param loginId 현재 로그인한 회원 로그인 아이디
     * @return 보증금 거래내역 식별키
     */
    public Long createTrade(CreateTradeDto dto, String loginId) {
        Account account = getAccountById(dto.getAccountId());
        QrCode qrCode = getQrCodeById(dto.getQrCodeId());
        Member member = getMemberByLoginId(loginId);

        checkDuplicatedTrade(qrCode.getQrCodeId(), account.getId());
        checkAccountEqual(account, qrCode);

        ApiResponse<TransferResponse> transferResponse = shinHanApiClient.transfer(
            createTransferRequest(account, qrCode, member.getName()));

        withdrawal(transferResponse, account);

        Trade trade = dto.toEntity(account, qrCode, member);

        return tradeRepository.save(trade).getId();
    }

    /**
     * 간편 송금
     *
     * @param dto     간편 송금 요청 정보
     * @param loginId 현재 로그인한 사용자 로그인 아이디
     */
    public Boolean simpleTrade(SimpleTradeDto dto, String loginId) {
        Account account = getAccountById(dto.getAccountId());
        SimpleQrCode simpleQrCode = getSimpleQrCodeById(dto.getSimpleQrCodeId());
        Member member = getMemberByLoginId(loginId);

        TransferRequest request = dto.toTransferRequest(account, simpleQrCode, member.getName());

        ApiResponse<TransferResponse> response = shinHanApiClient.transfer(request);

        withdrawal(response, account);

        return true;
    }

    /**
     * 보증금 반환
     *
     * @param dtos    보증금 반환 객체 리스트
     * @param loginId 로그인한 회원 로그인 아이디
     * @return 반환여부
     */
    public Boolean returnDeposits(List<ReturnDepositDto> dtos, String loginId) {
        List<Report> reports = new ArrayList<>();
        Member member = memberQueryRepository.getMemberByLoginId(loginId);

        for (ReturnDepositDto dto : dtos) {
            Trade trade = getTradeById(dto.getTradeId());

            checkIsDone(trade.getStatus());

            MemberAccountDto depositAccount = getDepositAccount(dto.getTradeId());
            MemberAccountDto withdrawalAccount = getWithdrawalAccount(dto.getTradeId());

            if (!isWithReason(dto)) {
                returnWithReason(reports, member, dto, trade, depositAccount, withdrawalAccount);
            } else {
                returnWithoutReason(dto, trade, withdrawalAccount);
            }
        }

        reportRepository.saveAll(reports);
        return true;
    }

    /**
     * 계좌 정보 조회
     *
     * @param accountId 계좌 식별키
     * @return 계좌 정보
     */
    private Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 계좌입니다."));
    }

    /**
     * QR 코드 정보 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @return QR 코드 정보
     */
    private QrCode getQrCodeById(Long qrCodeId) {
        return qrCodeRepository.findById(qrCodeId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 QR 코드입니다."));
    }

    /**
     * 간편 송금 QR 코드 정보 조회
     *
     * @param simpleQrCodeId 간편 송금 QR 코드 식별키
     * @return 간편 송금 QR 코드 정보
     */
    private SimpleQrCode getSimpleQrCodeById(Long simpleQrCodeId) {
        return simpleQrCodeRepository.findById(simpleQrCodeId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 QR 코드입니다."));
    }

    /**
     * 로그인 아이디로 회원 정보 조회
     *
     * @param loginId 현재 로그인 중인 회원의 로그인 아이디
     * @return 회원 정보
     */
    private Member getMemberByLoginId(String loginId) {
        return memberQueryRepository.getMemberByLoginId(loginId);
    }

    /**
     * 동일 계좌 여부 확인
     *
     * @param account 계좌
     * @param qrCode  QR 코드
     * @throws IllegalArgumentException 송금 계좌와 입금 계좌가 동일한 경우
     */
    private void checkAccountEqual(Account account, QrCode qrCode) {
        if (account.getAccountNumber().equals(qrCode.getAccount().getAccountNumber())) {
            throw new IllegalArgumentException("동일한 계좌 번호입니다.");
        }
    }

    /**
     * 보증금 송금용 신한은행 이제 API 요청 객체 생성
     *
     * @param account    보증금 송금할 계좌
     * @param qrCode     보증금 송금 QR 코드
     * @param memberName 송금 회원 이름
     * @return 신한은행 이체 API 요청 객체
     */
    private TransferRequest createTransferRequest(Account account, QrCode qrCode, String memberName) {
        return TransferRequest.builder()
                .withdrawalAccountNumber(account.getAccountNumber())
                .depositBankCode("088")
                .depositAccountNumber(changyoAccountNumber)
                .amount(qrCode.getAmount())
                .depositMemo(qrCode.getTitle())
                .withdrawalMemo(memberName)
                .build();
    }

    /**
     * 보증금 송금 (출금)
     *
     * @param transferResponse 신한은행 이체 API 응답
     * @param account          출금 계좌
     */
    private void withdrawal(ApiResponse<TransferResponse> transferResponse, Account account) {
        if (!checkIsOk(transferResponse.getStatus())) {
            throw new IllegalArgumentException("계좌 잔액이 부족합니다.");
        }
        account.editBalance(transferResponse.getData().getResult());
    }

    /**
     * 신한 이체 API 성공 여부 확인
     *
     * @param status 상태코드
     * @return true: 이체 성곧 시 false: 이체 실패 시
     */
    private boolean checkIsOk(HttpStatus status) {
        return status.equals(HttpStatus.OK);
    }

    /**
     * 거래내역 조회
     *
     * @param tradeId 거래내역 식별키
     * @return 조회된 거래내역
     */
    private Trade getTradeById(Long tradeId) {
        return tradeRepository.findById(tradeId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 거래내역 입니다."));
    }

    /**
     * 반환완료된 거래 여부 확인
     *
     * @param tradeStatus 거래 상태
     * @throws IllegalArgumentException 거래내역의 거래 상태가 반환대기가 아닌 경우
     */
    private void checkIsDone(TradeStatus tradeStatus) {
        if (!tradeStatus.equals(TradeStatus.WAIT)) {
            throw new IllegalArgumentException("이미 처리된 요청입니다.");
        }
    }

    /**
     * 사유 존재 여부
     *
     * @param dto 반환정보
     * @return true: 반환금액이 빈 문자열인 경우 false: 반환금액이 빈 문자열이 아닌 경우
     */
    private boolean isWithReason(ReturnDepositDto dto) {
        return dto.getReason().isEmpty();
    }

    /**
     * 출금 계좌 정보 조회
     *
     * @param tradeId 거래내역 식별키
     * @return 조회된 출금 계좌 정보
     */
    private MemberAccountDto getWithdrawalAccount(Long tradeId) {
        return tradeQueryRepository.getWithdrawalAccountByTradeId(tradeId);
    }

    /**
     * 입금 계좌 정보 조회
     *
     * @param tradeId 거래내역 식별키
     * @return 조회된 입금 계좌 정보
     */
    private MemberAccountDto getDepositAccount(Long tradeId) {
        return tradeQueryRepository.getDepositAccountByTradeId(tradeId);
    }

    /**
     * 보증금 반환 실행
     *
     * @param dto    반환받을 회원의 계좌 정보
     * @param amount 반환 금액
     */
    private void returnDeposit(MemberAccountDto dto, int amount) {
        TransferRequest transferRequest = createReturnTransferRequest(dto, amount);
        ApiResponse<TransferResponse> transferResponse = shinHanApiClient.transfer(transferRequest);

        Account account = getAccountById(dto.getAccountId());
        withdrawal(transferResponse, account);
    }

    /**
     * 보증금 반환용 신한은행 이체 API 요청 객체 생성
     *
     * @param dto    반환받을 회원의 계좌 정보
     * @param amount 금액
     * @return 신한은행 이체 API 요청 객체
     */
    private TransferRequest createReturnTransferRequest(MemberAccountDto dto, int amount) {
        return TransferRequest.builder()
                .withdrawalAccountNumber(changyoAccountNumber)
                .depositBankCode("088")
                .depositAccountNumber(dto.getAccountNumber())
                .amount(amount)
                .depositMemo(dto.getMemberName())
                .withdrawalMemo("챙겨요")
                .build();
    }

    /**
     * 사유가 존재하는 반환 (수수료 제외하고 반환)
     *
     * @param reports           저장될 사유 목록
     * @param member            보증금을 반환 할 회원
     * @param dto               반환 정보
     * @param trade             거래내역
     * @param depositAccount    보증금을 반환 할 회원
     * @param withdrawalAccount 보증금을 보냈던 회원
     */
    private void returnWithReason(List<Report> reports, Member member, ReturnDepositDto dto, Trade trade, MemberAccountDto depositAccount, MemberAccountDto withdrawalAccount) {
        returnDeposit(depositAccount, dto.getFee());
        returnDeposit(withdrawalAccount, dto.getAmount() - dto.getFee());

        trade.editStatus(TradeStatus.FEE);
        reports.add(dto.toEntity(trade, member));
    }

    /**
     * 사유가 존재하지 않는 반환 (원금 반환)
     *
     * @param dto               반환 정보
     * @param trade             거래내역
     * @param withdrawalAccount 반환받을 계좌
     */
    private void returnWithoutReason(ReturnDepositDto dto, Trade trade, MemberAccountDto withdrawalAccount) {
        returnDeposit(withdrawalAccount, dto.getAmount());
        trade.editStatus(TradeStatus.DONE);
    }

    /**
     * 중복 이체 검사
     *
     * @param qrCodeId QR 코드 식별키
     * @param tradeId  거래내역 식별키
     * @throws DuplicateException 중복 이체 요청인 경우
     */
    private void checkDuplicatedTrade(Long qrCodeId, Long tradeId) {
        LocalDateTime lastCreatedDate = tradeQueryRepository.getLastCreatedDateIdByQrCodeIdAndAccountId(qrCodeId, tradeId);

        if (isDuplicatedTrade(lastCreatedDate)) {
            throw new DuplicateException("이미 요청된 거래입니다.");
        }
    }

    /**
     * 중복된 거래가 존재하는지 확인
     *
     * @param lastCreatedDate 마지막 생성 시각
     * @return true: 마지막 생성 시각이 존재하고 최근에 수정된 경우 false: 마지막 생성 시각이 존재하지 않거나 최근에 수정되지 않은 경우
     */
    private boolean isDuplicatedTrade(LocalDateTime lastCreatedDate) {
        return isPresentTrade(lastCreatedDate) && isModifiedRecently(lastCreatedDate);
    }

    /**
     * 거래내역 존재 여부 확인
     *
     * @param lastCreatedDate 마지막 생성 시각
     * @return true: 마지막 생성 시각이 존재하는 경우 false: 마지막 생성 시각이 존재하지 않는 경우
     */
    private boolean isPresentTrade(LocalDateTime lastCreatedDate) {
        return lastCreatedDate != null;
    }

    /**
     * 최근 (3분 이내)에 수정되었는지 여부 확인
     *
     * @param lastCreatedDate 마지막 생성 시각
     * @return true: 현재 시각과의 차이가 3분 미만인 경우 false: 현재 시각과의 차이가 3분 이상인 경우
     */
    private boolean isModifiedRecently(LocalDateTime lastCreatedDate) {
        return getMinutesSinceLastModifiedDate(lastCreatedDate) < 1;
    }

    /**
     * 마지막 생성 시각과 현재 시각의 차이 (분) 계산
     *
     * @param lastCreatedDate 마지막 생성 시각
     * @return 마지막 생성 시각과 현재 시각 간의 차이 (분)
     */
    private long getMinutesSinceLastModifiedDate(LocalDateTime lastCreatedDate) {
        return Duration.between(lastCreatedDate, LocalDateTime.now()).toMinutes();
    }
}
