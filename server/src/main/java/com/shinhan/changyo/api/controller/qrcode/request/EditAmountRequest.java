package com.shinhan.changyo.api.controller.qrcode.request;

import com.shinhan.changyo.api.service.qrcode.dto.EditAmountDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EditAmountRequest {

    @NotBlank
    private int amount;

    @Builder
    public EditAmountRequest(int amount) {
        this.amount = amount;
    }

    public EditAmountDto toEditAmountDto(Long qrCodeId) {
        return EditAmountDto.builder()
                .amount(this.amount)
                .qrCodeId(qrCodeId)
                .build();
    }
}
