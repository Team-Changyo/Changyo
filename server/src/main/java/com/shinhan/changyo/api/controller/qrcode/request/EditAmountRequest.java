package com.shinhan.changyo.api.controller.qrcode.request;

import com.shinhan.changyo.api.service.qrcode.dto.EditAmountDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditAmountRequest {

    private int amount;

    @Builder
    public EditAmountRequest(int amount) {
        this.amount = amount;
    }

    public EditAmountDto toDto(Long qrCodeId) {
        return EditAmountDto.builder()
                .amount(amount)
                .qrCodeId(qrCodeId)
                .build();
    }
}
