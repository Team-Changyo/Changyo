package com.shinhan.api.docs.account;

import com.shinhan.api.api.controller.account.AccountController;
import com.shinhan.api.api.controller.account.request.AccountRequest;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.api.service.account.AccountQueryService;
import com.shinhan.api.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

public class AccountControllerDocsTest extends RestDocsSupport {

    private final AccountQueryService accountQueryService = mock(AccountQueryService.class);

    @Override
    protected Object initController() {
        return new AccountController(accountQueryService);
    }

    @DisplayName("계좌잔액조회 API")
    @Test
    void getAccountBalance() throws Exception {

        AccountRequest request = AccountRequest.builder()
            .accountNumber("110184999999")
            .build();

        AccountResponse response = AccountResponse.builder()
            .accountNumber("110184999999")
            .balance(331551)
            .build();

        given(accountQueryService.getAccountBalance(anyString()))
            .willReturn(response);

        mockMvc.perform(
                post("/v1/account/balance/detail")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("account-balance",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                        .description("출금계좌번호")
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
                    fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                        .description("출금계좌번호"),
                    fieldWithPath("data.balance").type(JsonFieldType.NUMBER)
                        .description("지불가능금액")
                )
            ));

    }
}
