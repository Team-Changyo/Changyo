package com.shinhan.changyo.api.controller.trade.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class ReturnRequest {

    @NotEmpty
    private List<ReturnDepositRequest> returnRequests;

    @Builder
    public ReturnRequest(List<ReturnDepositRequest> returnRequests) {
        this.returnRequests = returnRequests;
    }
}
