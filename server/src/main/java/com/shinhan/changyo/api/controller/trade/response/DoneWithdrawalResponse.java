package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DoneWithdrawalResponse {

    private Boolean hasNextPage;
    private int totalCount;
    private List<DoneWithdrawalDetailResponse> doneWithdrawals;

    @Builder
    public DoneWithdrawalResponse(Boolean hasNextPage, int totalCount, List<DoneWithdrawalDetailResponse> doneWithdrawals) {
        this.hasNextPage = hasNextPage;
        this.totalCount = totalCount;
        this.doneWithdrawals = doneWithdrawals;
    }

    public static DoneWithdrawalResponse of(Boolean hasNextPage, int totalCount, List<DoneWithdrawalDetailResponse> doneWithdrawals) {
        return DoneWithdrawalResponse.builder()
                .hasNextPage(hasNextPage)
                .totalCount(totalCount)
                .doneWithdrawals(doneWithdrawals)
                .build();
    }
}
