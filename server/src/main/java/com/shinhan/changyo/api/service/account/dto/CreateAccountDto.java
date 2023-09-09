package com.shinhan.changyo.api.service.account.dto;

import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateAccountDto {
    private Long memberId;
    private String customerName;
    private String bankCode;
    private String accountNumber;
    private String productName;
    private String title;
    private Boolean mainAccount;

    @Builder
    public CreateAccountDto(Long memberId, String customerName, String bankCode, String accountNumber, String productName, String title, Boolean mainAccount) {
        this.memberId = memberId;
        this.customerName = customerName;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.productName = productName;
        this.title = title;
        this.mainAccount = mainAccount;
    }

    public Account toEntity(Member member, int balance) {
        return Account.builder()
                .bankCode(this.bankCode)
                .accountNumber(this.accountNumber)
                .balance(balance)
                .productName(this.productName)
                .customerName(this.customerName)
                .title(this.title)
                .mainAccount(this.mainAccount)
                .active(true)
                .member(member)
                .build();
    }
}
