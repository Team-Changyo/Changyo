package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class WithdrawalResponse {

    private int remainCount;
    private int finishCount;
    private List<WithdrawalDetailResponse> withdrawalDetailResponses;

    @Builder
    public WithdrawalResponse(int remainCount, int finishCount, List<WithdrawalDetailResponse> withdrawalDetailResponses) {
        this.remainCount = remainCount;
        this.finishCount = finishCount;
        this.withdrawalDetailResponses = withdrawalDetailResponses;
    }
}
