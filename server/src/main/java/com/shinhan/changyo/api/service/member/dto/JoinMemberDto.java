package com.shinhan.changyo.api.service.member.dto;

import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;

@Data
public class JoinMemberDto {

    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;
    private String role;

    @Builder
    private JoinMemberDto(String loginId, String password, String name, String phoneNumber, String role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Member toEntity(String encryptedPwd) {
        return Member.builder()
            .loginId(this.loginId)
            .encryptedPwd(encryptedPwd)
            .name(this.name)
            .phoneNumber(this.phoneNumber)
            .active(true)
            .roles(Collections.singletonList(this.role))
            .build();
    }
}
