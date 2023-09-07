package com.shinhan.changyo.api.member.service.dto;

import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class JoinMemberDto {

    private String loginId;

    private String password;

    private String name;

    private String phoneNumber;

    @Builder
    public JoinMemberDto(String loginId, String password, String name, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Member toEntity(String encryptedPwd) {
        return Member.builder()
                .loginId(this.loginId)
                .encryptedPwd(encryptedPwd)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .active(true)
                .build();
    }
}
