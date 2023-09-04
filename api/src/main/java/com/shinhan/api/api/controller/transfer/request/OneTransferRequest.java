package com.shinhan.api.api.controller.transfer.request;

import lombok.Data;

@Data
public class OneTransferRequest {

    private String bankCode;
    private String accountNumber;
    private String memo;
}
