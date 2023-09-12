package com.shinhan.changyo.docs.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shinhan.changyo.api.controller.auth.AuthenticationController;
import com.shinhan.changyo.api.controller.auth.request.AuthenticationRequest;
import com.shinhan.changyo.api.controller.auth.request.CheckAuthenticationRequest;
import com.shinhan.changyo.api.service.auth.AuthenticationService;
import com.shinhan.changyo.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerDocsTest extends RestDocsSupport {

    private final AuthenticationService authenticationService = mock(AuthenticationService.class);

    @Override
    protected Object initController() {
        return new AuthenticationController(authenticationService);
    }

    @DisplayName("1원 송금")
    @Test
    void authenticationAccount() throws Exception {
        AuthenticationRequest request = AuthenticationRequest.builder()
            .bankCode("088")
            .accountNumber("110184999999")
            .build();

        given(authenticationService.generateAuthenticationNumber(anyString(), anyString()))
            .willReturn("123");

        mockMvc.perform(
                post("/authentication")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("authentication-account",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("bankCode").type(JsonFieldType.STRING)
                        .description("은행코드"),
                    fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                        .description("계좌번호")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.STRING)
                        .description("인증번호")
                )
            ));
    }

    @DisplayName("인증번호 확인 API")
//    @Test
    void checkAuthenticationNumber() throws Exception {
        CheckAuthenticationRequest request = CheckAuthenticationRequest.builder()
            .authenticationNumber("123")
            .build();
        
        mockMvc.perform(
                post("/authentication/check")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("authentication-check-account",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("authenticationNumber").type(JsonFieldType.STRING)
                        .description("인증번호")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.NULL)
                        .description("결과")
                )
            ));
    }
}
