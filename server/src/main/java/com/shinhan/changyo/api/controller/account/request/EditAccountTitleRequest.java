package com.shinhan.changyo.api.controller.account.request;

import com.shinhan.changyo.api.service.account.dto.EditAccountTitleDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EditAccountTitleRequest {
    @NotBlank
    private String title;

    @Builder
    public EditAccountTitleRequest(String title) {
        this.title = title;
    }

    public EditAccountTitleDto toEditAccountTitleDto(Long accountId){
        return EditAccountTitleDto.builder()
                .title(this.title)
                .accountId(accountId)
                .build();
    }
}
