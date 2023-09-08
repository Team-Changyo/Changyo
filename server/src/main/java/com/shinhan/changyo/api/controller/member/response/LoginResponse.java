package com.shinhan.changyo.api.controller.member.response;

import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse {

    private Long memberId;

    private String name;

    @Builder
    public LoginResponse(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public static LoginResponse of(Member member) {
        return LoginResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .build();
    }
}
