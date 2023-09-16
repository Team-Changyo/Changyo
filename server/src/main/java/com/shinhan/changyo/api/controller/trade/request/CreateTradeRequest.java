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

    @Builder
    public CreateTradeRequest(Long accountId, Long qrCodeId) {
        this.accountId = accountId;
        this.qrCodeId = qrCodeId;
    }

    public CreateTradeDto toCreateTradeDto() {
        return CreateTradeDto.builder()
                .accountId(this.accountId)
                .qrCodeId(this.qrCodeId)
                .build();
    }
}
