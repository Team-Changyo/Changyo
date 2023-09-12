package com.shinhan.changyo.api.controller.member.response;

import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class JoinMemberResponse {

    private String loginId;
    private String name;
    private String phoneNumber;
    private String role;

    @Builder
    public JoinMemberResponse(String loginId, String name, String phoneNumber, String role) {
        this.loginId = loginId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public static JoinMemberResponse of(Member member) {
        return JoinMemberResponse.builder()
            .loginId(member.getLoginId())
            .name(member.getName())
            .phoneNumber(member.getPhoneNumber())
            .role(member.getRoles().get(0))
            .build();
    }
}
