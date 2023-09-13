package com.shinhan.changyo.api.controller.member.response;

import com.shinhan.changyo.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberResponse {

    private String name;
    private String phoneNumber;
    private String role;

    @Builder
    public MemberResponse(String name, String phoneNumber, String role) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
            .name(member.getName())
            .phoneNumber(member.getPhoneNumber())
            .role(member.getRoles().get(0))
            .build();
    }
}
