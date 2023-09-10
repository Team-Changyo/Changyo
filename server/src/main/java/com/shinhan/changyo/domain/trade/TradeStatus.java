package com.shinhan.changyo.domain.trade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TradeStatus {
    WAIT("반환 전"),
    DONE("반환 완료"),
    FEE("수수료");
    
    private final String text;
}
