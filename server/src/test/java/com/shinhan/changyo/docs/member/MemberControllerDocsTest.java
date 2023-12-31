package com.shinhan.changyo.docs.member;

import com.shinhan.changyo.api.controller.member.MemberController;
import com.shinhan.changyo.api.controller.member.request.JoinRequest;
import com.shinhan.changyo.api.controller.member.request.LoginRequest;
import com.shinhan.changyo.api.controller.member.request.WithdrawalRequest;
import com.shinhan.changyo.api.controller.member.response.JoinMemberResponse;
import com.shinhan.changyo.api.controller.member.response.LoginResponse;
import com.shinhan.changyo.api.controller.member.response.MemberResponse;
import com.shinhan.changyo.api.service.member.MemberAccountService;
import com.shinhan.changyo.api.service.member.MemberService;
import com.shinhan.changyo.api.service.member.dto.JoinMemberDto;
import com.shinhan.changyo.docs.RestDocsSupport;
import com.shinhan.changyo.security.TokenInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberControllerDocsTest.class)
public class MemberControllerDocsTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);
    private final MemberAccountService memberAccountService = mock(MemberAccountService.class);

    @Override
    protected Object initController() {
        return new MemberController(memberService, memberAccountService);
    }

    @DisplayName("회원 가입 API")
    @Test
    void join() throws Exception {
        JoinRequest request = JoinRequest.builder()
            .loginId("ssafy")
            .password("ssafy1234")
            .name("김싸피")
            .phoneNumber("010-1234-5678")
            .role("MEMBER")
            .build();

        JoinMemberResponse response = JoinMemberResponse.builder()
            .loginId("ssafy")
            .name("김싸피")
            .phoneNumber("010-1234-5678")
            .role("MEMBER")
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
                        .description("전화번호"),
                    fieldWithPath("role").type(JsonFieldType.STRING)
                        .description("역할")
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
                        .description("전화번호"),
                    fieldWithPath("data.role").type(JsonFieldType.STRING)
                        .description("역할")
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

        TokenInfo token = TokenInfo.builder()
            .grantType("Bearer")
            .accessToken("accessToken")
            .refreshToken("refreshToken")
            .build();


        given(memberAccountService.login(anyString(), anyString()))
            .willReturn(token);

        mockMvc.perform(
                post("/login")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("login-member",
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
                    fieldWithPath("data.grantType").type(JsonFieldType.STRING)
                        .description("grantType"),
                    fieldWithPath("data.accessToken").type(JsonFieldType.STRING)
                        .description("accessToken"),
                    fieldWithPath("data.refreshToken").type(JsonFieldType.STRING)
                        .description("refreshToken")
                )
            ));
    }

    @DisplayName("회원탈퇴 API")
    @Test
    void withdrawal() throws Exception {
        WithdrawalRequest request = WithdrawalRequest.builder()
            .loginId("ssafy")
            .password("ssafy1234")
            .build();

        Boolean result = true;

        given(memberService.withdrawal(anyString(), anyString()))
            .willReturn(result);

        mockMvc.perform(
                post("/withdrawal")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isFound())
            .andDo(document("remove-member",
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
                    fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                        .description("탈퇴 여부")
                )
            ));
    }

    @DisplayName("회원 정보조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getInfo() throws Exception {
        MemberResponse response = MemberResponse.builder()
            .name("홍진식")
            .phoneNumber("010-1234-1234")
            .role("MEMBER")
            .build();

        given(memberService.getInfo(anyString()))
            .willReturn(response);

        mockMvc.perform(
                get("/info")
                    .header("Authentication", "test")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-member",
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING)
                        .description("이름"),
                    fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING)
                        .description("연락처"),
                    fieldWithPath("data.role").type(JsonFieldType.STRING)
                        .description("역할")
                )
            ));
    }
}
