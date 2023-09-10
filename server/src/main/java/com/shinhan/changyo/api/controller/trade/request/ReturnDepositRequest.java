package com.shinhan.changyo.api.controller.trade.request;

import com.shinhan.changyo.api.service.trade.dto.ReturnDepositDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReturnDepositRequest {

    @NotNull
    private Long tradeId;
    private int amount;
    private int fee;
    @NotBlank
    private String reason;
    private String description;

    @Builder
    public ReturnDepositRequest(Long tradeId, int amount, int fee, String reason, String description) {
        this.tradeId = tradeId;
        this.amount = amount;
        this.fee = fee;
        this.reason = reason;
        this.description = description;
    }

    public ReturnDepositDto toReturnDepositDto() {
        return ReturnDepositDto.builder()
                .tradeId(this.tradeId)
                .amount(this.amount)
                .fee(this.fee)
                .reason(this.reason)
                .description(this.description)
                .build();
    }
}
