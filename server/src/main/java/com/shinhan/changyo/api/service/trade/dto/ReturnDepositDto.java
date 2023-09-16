package com.shinhan.changyo.api.service.trade.dto;

import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.report.Report;
import com.shinhan.changyo.domain.trade.Trade;
import lombok.Builder;
import lombok.Data;

@Data
public class ReturnDepositDto {
    private Long tradeId;
    private int amount;
    private int fee;
    private String reason;
    private String description;

    @Builder
    public ReturnDepositDto(Long tradeId, int amount, int fee, String reason, String description) {
        this.tradeId = tradeId;
        this.amount = amount;
        this.fee = fee;
        this.reason = reason;
        this.description = description;
    }

    public Report toEntity(Trade trade, Member member) {
        return Report.builder()
                .reason(this.reason)
                .description(this.description)
                .fee(this.fee)
                .amount(this.amount)
                .member(member)
                .trade(trade)
                .build();
    }
}
