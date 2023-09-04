package com.shinhan.api.api.controller.transfer.response;

import lombok.Data;

@Data
public class TransferResponse {

    private String withdrawalAccountNumber;
    private String depositBankCode;
    private String depositAccountNumber;
    private int amount;
    private String depositMemo;
    private String withdrawalMemo;
    private int result;
}
