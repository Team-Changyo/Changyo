package com.shinhan.changyo.api.service.transfer;

import com.shinhan.changyo.api.controller.transfer.response.ClientAccountResponse;
import com.shinhan.changyo.api.controller.transfer.response.StoreAccountResponse;
import com.shinhan.changyo.api.controller.transfer.response.TransferInfoResponse;
import com.shinhan.changyo.domain.transfer.TransferQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 이체정보 조회 서비스
 *
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TransferQueryService {

    private final TransferQueryRepository transferQueryRepository;

    /**
     * 보증금 이체 정보 조회
     *
     * @param qrCodeId QR 코드 식별키
     * @param loginId  현재 로그인 중인 회원 로그인 아이디
     * @return 보증금 이체 정보
     */
    public TransferInfoResponse getTransferInfo(Long qrCodeId, String loginId) {
        ClientAccountResponse clientAccount = transferQueryRepository.getClientAccount(loginId);
        StoreAccountResponse storeAccount = transferQueryRepository.getStoreAccount(qrCodeId);

        return TransferInfoResponse.of(storeAccount, clientAccount);
    }
}
