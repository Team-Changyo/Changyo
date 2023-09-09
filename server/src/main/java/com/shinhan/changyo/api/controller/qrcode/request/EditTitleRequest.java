package com.shinhan.changyo.api.controller.qrcode.request;

import com.shinhan.changyo.api.service.qrcode.dto.EditTitleDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EditTitleRequest {
    @NotBlank
    private String title;

    @Builder
    public EditTitleRequest(String title) {
        this.title = title;
    }


    public EditTitleDto toEditTitleDto(Long qrCodeId){
        return EditTitleDto.builder()
                .qrCodeId(qrCodeId)
                .title(this.title)
                .build();
    }
}
