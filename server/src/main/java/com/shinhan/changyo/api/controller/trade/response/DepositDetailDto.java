package com.shinhan.changyo.api.controller.trade.response;

import com.shinhan.changyo.domain.trade.TradeStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DepositDetailDto {
    private Long tradeId;
    private TradeStatus status;
    private String memberName;
    private String tradeDate;

    @Builder
    public DepositDetailDto(Long tradeId, TradeStatus status, String memberName, LocalDateTime tradeDate) {
        this.tradeId = tradeId;
        this.status = status;
        this.memberName = memberName;
        this.tradeDate = tradeDate.format(DateTimeFormatter.ofPattern("yyyyMMdd hhmm"));
    }
}
