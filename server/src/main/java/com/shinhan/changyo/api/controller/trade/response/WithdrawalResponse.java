package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class WithdrawalResponse {

    private int waitCount;
    private int doneCount;
    private List<WithdrawalDetailResponse> waitWithdrawals;
    private List<WithdrawalDetailResponse> doneWithdrawals;

    @Builder
    public WithdrawalResponse(int waitCount, int doneCount, List<WithdrawalDetailResponse> waitWithdrawals, List<WithdrawalDetailResponse> doneWithdrawals) {
        this.waitCount = waitCount;
        this.doneCount = doneCount;
        this.waitWithdrawals = waitWithdrawals;
        this.doneWithdrawals = doneWithdrawals;
    }
}
