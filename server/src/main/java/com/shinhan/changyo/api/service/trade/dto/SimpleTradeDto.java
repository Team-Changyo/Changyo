package com.shinhan.changyo.api.service.trade.dto;

import com.shinhan.changyo.client.request.TransferRequest;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.qrcode.SimpleQrCode;
import lombok.Builder;
import lombok.Data;

@Data
public class SimpleTradeDto {

    private Long accountId;
    private Long simpleQrCodeId;

    @Builder
    public SimpleTradeDto(Long accountId, Long simpleQrCodeId) {
        this.accountId = accountId;
        this.simpleQrCodeId = simpleQrCodeId;
    }

    public TransferRequest toTransferRequest(Account account, SimpleQrCode simpleQrCode, String memberName) {
        return TransferRequest.builder()
                .withdrawalAccountNumber(account.getAccountNumber())
                .depositBankCode(account.getBankCode())
                .depositAccountNumber(simpleQrCode.getAccountNumber())
                .amount(simpleQrCode.getAmount())
                .depositMemo(simpleQrCode.getMemberName())
                .withdrawalMemo(memberName)
                .build();
    }
}
