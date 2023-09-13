package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class WaitWithdrawalResponse {

    private int totalCount;
    private List<WaitWithdrawalDetailResponse> waitWithdrawals;

    @Builder
    public WaitWithdrawalResponse(int totalCount, List<WaitWithdrawalDetailResponse> waitWithdrawals) {
        this.totalCount = totalCount;
        this.waitWithdrawals = waitWithdrawals;
    }

    public static WaitWithdrawalResponse of(int totalCount, List<WaitWithdrawalDetailResponse> withdrawals) {
        return WaitWithdrawalResponse.builder()
                .totalCount(totalCount)
                .waitWithdrawals(withdrawals)
                .build();
    }
}
