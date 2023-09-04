package com.shinhan.api.api.controller.account.request;

import lombok.Data;

@Data
public class CustomerNameRequest {

    private String bankCode;
    private String accountNumber;
}
