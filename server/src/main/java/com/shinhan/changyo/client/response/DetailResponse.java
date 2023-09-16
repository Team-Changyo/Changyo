package com.shinhan.changyo.client.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailResponse {
    private int balance;
    private String productName;
    private String customerName;

    @Builder
    public DetailResponse(int balance, String productName, String customerName) {
        this.balance = balance;
        this.productName = productName;
        this.customerName = customerName;
    }
}
