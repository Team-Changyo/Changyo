package com.shinhan.changyo.api.controller.transfer.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SimpleTransferInfoResponse {
    private SimpleStoreAccountResponse storeAccount;
    private ClientAccountResponse clientAccount;

    @Builder
    public SimpleTransferInfoResponse(SimpleStoreAccountResponse storeAccount, ClientAccountResponse clientAccount) {
        this.storeAccount = storeAccount;
        this.clientAccount = clientAccount;
    }

    public static SimpleTransferInfoResponse of(SimpleStoreAccountResponse storeAccount, ClientAccountResponse clientAccount) {
        return SimpleTransferInfoResponse.builder()
                .clientAccount(clientAccount)
                .storeAccount(storeAccount)
                .build();
    }
}
