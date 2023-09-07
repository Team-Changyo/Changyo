package com.shinhan.changyo.docs.member;

import com.shinhan.changyo.api.controller.member.MemberController;
import com.shinhan.changyo.api.controller.member.request.JoinRequest;
import com.shinhan.changyo.api.controller.member.request.LoginRequest;
import com.shinhan.changyo.api.controller.member.response.JoinMemberResponse;
import com.shinhan.changyo.api.controller.member.response.LoginResponse;
import com.shinhan.changyo.api.service.member.AccountService;
import com.shinhan.changyo.api.service.member.MemberService;
import com.shinhan.changyo.api.service.member.dto.JoinMemberDto;
import com.shinhan.changyo.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerDocsTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);
    private final AccountService accountService = mock(AccountService.class);

    @Override
    protected Object initController() {
        return new MemberController(memberService, accountService);
    }

    @DisplayName("회원 가입 API")
    @Test
    void join() throws Exception {
        JoinRequest request = JoinRequest.builder()
                .loginId("ssafy")
                .password("ssafy1234")
                .name("김싸피")
                .phoneNumber("010-1234-5678")
                .build();

        JoinMemberResponse response = JoinMemberResponse.builder()
                .loginId("ssafy")
                .name("김싸피")
                .phoneNumber("010-1234-5678")
                .build();

        given(memberService.join(any(JoinMemberDto.class)))
                .willReturn(response);

        mockMvc.perform(
                        post("/join")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("loginId").type(JsonFieldType.STRING)
                                        .description("로그인 아이디"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("비밀번호"),
                                fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("이름"),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING)
                                        .description("전화번호")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답데이터"),
                                fieldWithPath("data.loginId").type(JsonFieldType.STRING)
                                        .description("로그인 아이디"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING)
                                        .description("이름"),
                                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING)
                                        .description("전화번호")
                        )
                ));
    }

    @DisplayName("로그인 API")
    @Test
    void login() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .loginId("ssafy")
                .password("ssafy1234")
                .build();

        LoginResponse response = LoginResponse.builder()
                .memberId(1L)
                .name("김싸피")
                .build();

        given(accountService.login(any(String.class), any(String.class)))
                .willReturn(response);

        mockMvc.perform(
                        post("/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("member-login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("loginId").type(JsonFieldType.STRING)
                                        .description("로그인 아이디"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답데이터"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER)
                                        .description("회원 식별키"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING)
                                        .description("이름")
                        )
                        ));
    }
}
