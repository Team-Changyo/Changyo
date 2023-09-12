package com.shinhan.changyo.api.controller.member.request;

import com.shinhan.changyo.api.service.member.dto.JoinMemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class JoinRequest {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String role;

    @Builder
    private JoinRequest(String loginId, String password, String name, String phoneNumber, String role) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public JoinMemberDto toJoinMemberDto() {
        return JoinMemberDto.builder()
                .loginId(this.loginId)
                .password(this.password)
                .name(this.name)
                .phoneNumber(this.phoneNumber)

                .build();
    }
}
