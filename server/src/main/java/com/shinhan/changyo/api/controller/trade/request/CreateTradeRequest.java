package com.shinhan.changyo.api.controller.trade.request;

import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateTradeRequest {

    @NotNull
    private Long accountId;
    @NotBlank
    private String withdrawalAccountNumber;
    @NotNull
    private Long qrCodeId;
    @NotBlank
    private String qrCodeTitle;
    @NotBlank
    private String depositAccountNumber;
    private int amount;
    @NotBlank
    private String content;

    @Builder
    public CreateTradeRequest(Long accountId, String withdrawalAccountNumber, Long qrCodeId, String qrCodeTitle, String depositAccountNumber, int amount, String content) {
        this.accountId = accountId;
        this.withdrawalAccountNumber = withdrawalAccountNumber;
        this.qrCodeId = qrCodeId;
        this.qrCodeTitle = qrCodeTitle;
        this.depositAccountNumber = depositAccountNumber;
        this.amount = amount;
        this.content = content;
    }

    public CreateTradeDto toCreateTradeDto() {
        return CreateTradeDto.builder()
                .accountId(this.accountId)
                .withdrawalAccountNumber(this.withdrawalAccountNumber)
                .qrCodeId(this.qrCodeId)
                .qrCodeTitle(this.qrCodeTitle)
                .depositAccountNumber(this.depositAccountNumber)
                .amount(this.amount)
                .content(this.content)
                .build();
    }
}
