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
    @NotNull
    private Long qrCodeId;
    private int amount;
    @NotBlank
    private String content;

    @Builder
    public CreateTradeRequest(Long accountId, Long qrCodeId, int amount, String content) {
        this.accountId = accountId;
        this.qrCodeId = qrCodeId;
        this.amount = amount;
        this.content = content;
    }

    public CreateTradeDto toCreateTradeDto() {
        return CreateTradeDto.builder()
                .accountId(this.accountId)
                .qrCodeId(this.qrCodeId)
                .amount(this.amount)
                .content(this.content)
                .build();
    }
}
