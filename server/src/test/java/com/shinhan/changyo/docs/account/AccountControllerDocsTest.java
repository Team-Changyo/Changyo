package com.shinhan.changyo.docs.account;

import com.shinhan.changyo.api.controller.account.AccountController;
import com.shinhan.changyo.api.controller.account.request.CreateAccountRequest;
import com.shinhan.changyo.api.service.account.AccountService;
import com.shinhan.changyo.api.service.account.dto.CreateAccountDto;
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

public class AccountControllerDocsTest extends RestDocsSupport {

    private final AccountService accountService = mock(AccountService.class);

    @Override
    protected Object initController() {
        return new AccountController(accountService);
    }

    @DisplayName("계좌 등록 API")
    @Test
    void createAccount() throws Exception {
        CreateAccountRequest request = CreateAccountRequest.builder()
                .memberId(1L)
                .customerName("김싸피")
                .bankCode("088")
                .accountNumber("110184999999")
                .productName("예금")
                .title("싸피월급통장")
                .mainAccount(true)
                .build();

        Long accountId = 1L;

        given(accountService.createAccount(any(CreateAccountDto.class)))
                .willReturn(accountId);

        mockMvc.perform(
                        post("/account")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-account",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("memberId").type(JsonFieldType.NUMBER)
                                        .description("회원 식별키"),
                                fieldWithPath("customerName").type(JsonFieldType.STRING)
                                        .description("고객명"),
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("은행코드"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호"),
                                fieldWithPath("productName").type(JsonFieldType.STRING)
                                        .description("상품명"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("별칭"),
                                fieldWithPath("mainAccount").type(JsonFieldType.BOOLEAN)
                                        .description("주계좌여부")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별키")
                        )
                ));
    }
}
