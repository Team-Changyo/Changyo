package com.shinhan.changyo.api.controller.account.request;

import com.shinhan.changyo.api.service.account.dto.EditAccountTitleDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EditAccountTitleRequest {

    @NotNull
    private Long accountId;
    @NotBlank
    private String title;

    @Builder
    public EditAccountTitleRequest(Long accountId, String title) {
        this.accountId = accountId;
        this.title = title;
    }

    public EditAccountTitleDto toEditAccountTitleDto(String loginId) {
        return EditAccountTitleDto.builder()
                .title(this.title)
                .accountId(this.accountId)
                .loginId(loginId)
                .build();
    }
}
