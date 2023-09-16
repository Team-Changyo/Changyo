package com.shinhan.changyo.api.service.trade.dto;

import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.qrcode.QrCode;
import com.shinhan.changyo.domain.trade.Trade;
import com.shinhan.changyo.domain.trade.TradeStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateTradeDto {
    private Long accountId;
    private Long qrCodeId;

    @Builder
    public CreateTradeDto(Long accountId, Long qrCodeId) {
        this.accountId = accountId;
        this.qrCodeId = qrCodeId;
    }

    public Trade toEntity(Account account, QrCode qrCode, Member member) {
        return Trade.builder()
                .summary("이체")
                .withdrawalAmount(qrCode.getAmount())
                .depositAmount(qrCode.getAmount())
                .content(member.getName())
                .balance(account.getBalance())
                .status(TradeStatus.WAIT)
                .dealershipName("챙겨요")
                .account(account)
                .qrCode(qrCode)
                .build();
    }
}
