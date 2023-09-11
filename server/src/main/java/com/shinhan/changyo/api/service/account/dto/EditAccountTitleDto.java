package com.shinhan.changyo.api.service.account.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditAccountTitleDto {
    private String title;

    private Long accountId;

    @Builder
    public EditAccountTitleDto(String title, Long accountId) {
        this.title = title;
        this.accountId = accountId;
    }

}
