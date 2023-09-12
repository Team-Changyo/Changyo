package com.shinhan.changyo.client.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class TradeRequest {

    @NotBlank(message = "계좌 번호는 필수입니다.")
    private String accountNumber;

    @Builder
    private TradeRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
