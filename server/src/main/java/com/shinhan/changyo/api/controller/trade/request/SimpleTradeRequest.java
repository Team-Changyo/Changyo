package com.shinhan.changyo.api.controller.trade.request;

import com.shinhan.changyo.api.service.trade.dto.SimpleTradeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SimpleTradeRequest {

    @NotNull
    private Long accountId;
    @NotNull
    private Long simpleQrCodeId;

    @Builder
    public SimpleTradeRequest(Long accountId, Long simpleQrCodeId) {
        this.accountId = accountId;
        this.simpleQrCodeId = simpleQrCodeId;
    }

    public SimpleTradeDto toSimpleTransferDto() {
        return SimpleTradeDto.builder()
                .accountId(this.accountId)
                .simpleQrCodeId(this.simpleQrCodeId)
                .build();
    }
}
