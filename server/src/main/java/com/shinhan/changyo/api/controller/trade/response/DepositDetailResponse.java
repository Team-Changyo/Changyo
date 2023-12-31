package com.shinhan.changyo.api.controller.trade.response;

import com.shinhan.changyo.api.service.trade.dto.DepositDetailDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DepositDetailResponse {

    private boolean hasNextPage;
    private String qrCodeTitle;
    private int amount;
    private int totalAmount;
    private int waitCount;
    private int doneCount;
    List<DepositDetailDto> waitDetails;
    List<DepositDetailDto> doneDetails;

    @Builder
    public DepositDetailResponse(boolean hasNextPage, String qrCodeTitle, int amount, int totalAmount, int waitCount, int doneCount, List<DepositDetailDto> waitDetails, List<DepositDetailDto> doneDetails) {
        this.hasNextPage = hasNextPage;
        this.qrCodeTitle = qrCodeTitle;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.waitCount = waitCount;
        this.doneCount = doneCount;
        this.waitDetails = waitDetails;
        this.doneDetails = doneDetails;
    }

}
