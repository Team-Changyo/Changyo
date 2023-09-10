package com.shinhan.changyo.api.service.qrcode.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditTitleDto {

    private Long qrCodeId;

    private String title;

    @Builder
    public EditTitleDto(Long qrCodeId, String title) {
        this.qrCodeId = qrCodeId;
        this.title = title;
    }
}
