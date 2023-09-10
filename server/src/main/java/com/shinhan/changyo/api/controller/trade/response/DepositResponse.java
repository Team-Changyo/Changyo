package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DepositResponse {

    private int totalCount;
    private List<DepositOverviewResponse> depositOverviews;

    @Builder
    public DepositResponse(int totalCount, List<DepositOverviewResponse> depositOverviews) {
        this.totalCount = totalCount;
        this.depositOverviews = depositOverviews;
    }

    public static DepositResponse of(int totalCount, List<DepositOverviewResponse> overviews) {
        return DepositResponse.builder()
                .totalCount(totalCount)
                .depositOverviews(overviews)
                .build();
    }
}
