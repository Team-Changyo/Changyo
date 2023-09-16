package com.shinhan.changyo.api.service.qrcode.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditTitleDto {

    private Long qrCodeId;

    private String title;

    private String loginId;

    @Builder
    public EditTitleDto(Long qrCodeId, String title, String loginId) {
        this.qrCodeId = qrCodeId;
        this.title = title;
        this.loginId = loginId;
    }
}
