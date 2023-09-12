package com.shinhan.changyo.api.controller.trade.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DepositResponse {

    private boolean hasNextPage;
    private int totalCount;
    private List<DepositOverviewResponse> depositOverviews;

    @Builder
    public DepositResponse(boolean hasNextPage, int totalCount, List<DepositOverviewResponse> depositOverviews) {
        this.hasNextPage = hasNextPage;
        this.totalCount = totalCount;
        this.depositOverviews = depositOverviews;
    }

    public static DepositResponse of(boolean hasNextPage, int totalCount, List<DepositOverviewResponse> overviews) {
        return DepositResponse.builder()
                .hasNextPage(hasNextPage)
                .totalCount(totalCount)
                .depositOverviews(overviews)
                .build();
    }
}
