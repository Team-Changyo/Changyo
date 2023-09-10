package com.shinhan.changyo.api.service.trade;

import com.shinhan.changyo.api.ApiResponse;
import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
import com.shinhan.changyo.api.service.trade.dto.ReturnDepositDto;
import com.shinhan.changyo.client.ShinHanApiClient;
import com.shinhan.changyo.client.TransferRequest;
import com.shinhan.changyo.client.TransferResponse;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import com.shinhan.changyo.domain.qrcode.QrCode;
import com.shinhan.changyo.domain.qrcode.repository.QrCodeRepository;
import com.shinhan.changyo.domain.trade.Trade;
import com.shinhan.changyo.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 보증금 송금
     *
     * @param dto 보증금 송금 정보
     * @return 보증금 거래내역 식별키
     */
    public Long createTrade(CreateTradeDto dto) {
        ApiResponse<TransferResponse> transferResponse = shinHanApiClient.transfer(createTransferRequest(dto));

        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 계좌입니다."));
        withdrawal(transferResponse, account);

        QrCode qrCode = qrCodeRepository.findById(dto.getQrCodeId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 QR 코드입니다."));
        Trade trade = dto.toEntity(account, qrCode);

        return tradeRepository.save(trade).getId();
    }

    /**
     * 보증금 반환
     *
     * @param dtos 보증금 반환 객체 리스트
     * @return 반환여부
     */
    public Boolean returnDeposit(List<ReturnDepositDto> dtos) {
        return false;
    }

    /**
     * 신한은행 이제 API 요청 객체 생성
     *
     * @param dto 보증금 송금 정보
     * @return 이체 API 요청 객체
     */
    private TransferRequest createTransferRequest(CreateTradeDto dto) {
        return TransferRequest.builder()
                .withdrawalAccountNumber(dto.getWithdrawalAccountNumber())
                .depositBankCode("088")
                .depositAccountNumber(changyoAccountNumber)
                .amount(dto.getAmount())
                .depositMemo(dto.getQrCodeTitle())
                .withdrawalMemo(dto.getContent())
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
}
