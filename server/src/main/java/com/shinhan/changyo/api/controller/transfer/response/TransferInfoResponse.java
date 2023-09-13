package com.shinhan.changyo.api.controller.transfer.response;

import lombok.Builder;
import lombok.Data;

@Data
public class TransferInfoResponse {

    private StoreAccountResponse storeAccount;
    private ClientAccountResponse clientAccount;

    @Builder
    public TransferInfoResponse(StoreAccountResponse storeAccount, ClientAccountResponse clientAccount) {
        this.storeAccount = storeAccount;
        this.clientAccount = clientAccount;
    }

    public static TransferInfoResponse of(StoreAccountResponse storeAccount, ClientAccountResponse clientAccount) {
        return TransferInfoResponse.builder()
                .clientAccount(clientAccount)
                .storeAccount(storeAccount)
                .build();
    }
}
