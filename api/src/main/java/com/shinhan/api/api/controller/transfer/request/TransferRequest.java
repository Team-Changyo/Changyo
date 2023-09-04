package com.shinhan.api.api.controller.transfer.request;

import lombok.Data;

@Data
public class TransferRequest {

    private String withdrawalAccountNumber;
    private String depositBankCode;
    private String depositAccountNumber;
    private int amount;
    private String depositMemo;
    private String withdrawalMemo;

}
