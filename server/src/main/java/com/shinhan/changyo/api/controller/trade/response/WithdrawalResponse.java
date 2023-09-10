package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class WithdrawalResponse {

    private int remainCount;
    private int finishCount;
    private List<WithdrawalDetailResponse> waitWithdrawals;
    private List<WithdrawalDetailResponse> doneWithdrawals;

    @Builder
    public WithdrawalResponse(int remainCount, int finishCount, List<WithdrawalDetailResponse> waitWithdrawals, List<WithdrawalDetailResponse> doneWithdrawals) {
        this.remainCount = remainCount;
        this.finishCount = finishCount;
        this.waitWithdrawals = waitWithdrawals;
        this.doneWithdrawals = doneWithdrawals;
    }
}
