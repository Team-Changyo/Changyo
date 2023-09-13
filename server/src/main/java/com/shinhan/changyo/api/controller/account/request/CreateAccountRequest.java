package com.shinhan.changyo.api.controller.account.request;

import com.shinhan.changyo.api.service.account.dto.CreateAccountDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateAccountRequest {
    @NotBlank
    private String customerName;
    @NotBlank
    private String bankCode;
    @NotBlank
    private String accountNumber;
    @NotBlank
    private String productName;
    @NotBlank
    private String title;
    @NotNull
    private Boolean mainAccount;

    @Builder
    public CreateAccountRequest(String customerName, String bankCode, String accountNumber, String productName, String title, Boolean mainAccount) {
        this.customerName = customerName;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.productName = productName;
        this.title = title;
        this.mainAccount = mainAccount;
    }

    public CreateAccountDto toCreateAccountDto(String loginId) {
        return CreateAccountDto.builder()
                .loginId(loginId)
                .customerName(this.customerName)
                .bankCode(this.bankCode)
                .accountNumber(this.accountNumber)
                .productName(this.productName)
                .title(this.title)
                .mainAccount(this.mainAccount)
                .build();
    }
}
