package com.shinhan.changyo.api.controller.member.response;

import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse {

    private String loginId;

    private String name;

    @Builder
    public LoginResponse(String loginId, String name) {
        this.loginId = loginId;
        this.name = name;
    }

    public static LoginResponse of(Member member) {
        return LoginResponse.builder()
                .loginId(member.getLoginId())
                .name(member.getName())
                .build();
    }
}
