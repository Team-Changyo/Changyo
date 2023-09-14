package com.shinhan.changyo.api.service.trade.dto;

import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.qrcode.QrCode;
import com.shinhan.changyo.domain.trade.Trade;
import com.shinhan.changyo.domain.trade.TradeStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateTradeDto {
    private Long accountId;
    private String withdrawalAccountNumber;
    private Long qrCodeId;
    private String qrCodeTitle;
    private String depositAccountNumber;
    private int amount;

    @Builder
    public CreateTradeDto(Long accountId, String withdrawalAccountNumber, Long qrCodeId, String qrCodeTitle, String depositAccountNumber, int amount) {
        this.accountId = accountId;
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.qrCodeId = qrCodeId;
        this.qrCodeTitle = qrCodeTitle;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
    }

    public Trade toEntity(Account account, QrCode qrCode) {
        return Trade.builder()
                .summary("이체")
                .withdrawalAmount(this.amount)
                .depositAmount(this.amount)
                .content(account.getMember().getName())
                .balance(account.getBalance())
                .status(TradeStatus.WAIT)
                .dealershipName("챙겨요")
                .account(account)
                .qrCode(qrCode)
                .build();
    }
}
