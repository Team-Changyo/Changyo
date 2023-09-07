package com.shinhan.changyo.api.member.controller.response;

import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class JoinMemberResponse {

    private String loginId;

    private String name;

    private String phoneNumber;

    @Builder
    public JoinMemberResponse(String loginId, String name, String phoneNumber) {
        this.loginId = loginId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static JoinMemberResponse of(Member member) {
        return JoinMemberResponse.builder()
                .loginId(member.getLoginId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
