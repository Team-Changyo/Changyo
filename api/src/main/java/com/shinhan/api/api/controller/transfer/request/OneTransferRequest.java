package com.shinhan.api.api.controller.transfer.request;

import com.shinhan.api.api.service.transfer.dto.OneTransferDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OneTransferRequest {

    private String bankCode;
    private String accountNumber;
    private String memo;

    @Builder
    public OneTransferRequest(String bankCode, String accountNumber, String memo) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.memo = memo;
    }

    public OneTransferDto toOneTransferDto() {
        return OneTransferDto.builder()
            .bankCode(this.bankCode)
            .accountNumber(this.accountNumber)
            .memo(this.memo)
            .build();
    }
}
