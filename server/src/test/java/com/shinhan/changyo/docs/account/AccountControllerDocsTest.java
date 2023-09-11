package com.shinhan.changyo.docs.account;

import com.shinhan.changyo.api.controller.account.AccountController;
import com.shinhan.changyo.api.controller.account.request.CreateAccountRequest;
import com.shinhan.changyo.api.controller.account.request.EditAccountTitleRequest;
import com.shinhan.changyo.api.controller.account.response.AccountDetailResponse;
import com.shinhan.changyo.api.controller.account.response.AccountEditResponse;
import com.shinhan.changyo.api.controller.account.response.AccountResponse;
import com.shinhan.changyo.api.service.account.AccountQueryService;
import com.shinhan.changyo.api.service.account.AccountService;
import com.shinhan.changyo.api.service.account.dto.CreateAccountDto;
import com.shinhan.changyo.api.service.account.dto.EditAccountTitleDto;
import com.shinhan.changyo.docs.RestDocsSupport;
import com.shinhan.changyo.docs.qrcode.QrCodeControllerDocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QrCodeControllerDocsTest.class)
public class AccountControllerDocsTest extends RestDocsSupport {

    private final AccountService accountService = mock(AccountService.class);
    private final AccountQueryService accountQueryService = mock(AccountQueryService.class);

    @Override
    protected Object initController() {
        return new AccountController(accountService, accountQueryService);
    }

    @DisplayName("계좌 등록 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void createAccount() throws Exception {
        CreateAccountRequest request = CreateAccountRequest.builder()
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

    @DisplayName("회원별 계좌 전체 조회 API")
    @Test
    void getAccounts() throws Exception {
        Long memberId = 1L;

        AccountDetailResponse account1 = AccountDetailResponse.builder()
                .accountNumber("110184999999")
                .balance(200501)
                .bankCode("088")
                .mainAccount(true)
                .build();

        AccountDetailResponse account2 = AccountDetailResponse.builder()
                .accountNumber("110185999999")
                .balance(0)
                .bankCode("088")
                .mainAccount(false)
                .build();

        List<AccountDetailResponse> accounts = List.of(account1, account2);

        AccountResponse response = AccountResponse.builder()
                .accountSize(accounts.size())
                .accountDetailResponses(accounts)
                .build();

        given(accountQueryService.getAccounts(anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/account")
                                .header("memberId", String.valueOf(memberId))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-account",
                        preprocessResponse(prettyPrint()),

                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.accountSize").type(JsonFieldType.NUMBER)
                                        .description("전체 계좌 개수"),
                                fieldWithPath("data.accountDetailResponses").type(JsonFieldType.ARRAY)
                                        .description("계좌 정보 데이터"),
                                fieldWithPath("data.accountDetailResponses[].accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호"),
                                fieldWithPath("data.accountDetailResponses[].balance").type(JsonFieldType.NUMBER)
                                        .description("잔액"),
                                fieldWithPath("data.accountDetailResponses[].bankCode").type(JsonFieldType.STRING)
                                        .description("은행코드"),
                                fieldWithPath("data.accountDetailResponses[].mainAccount").type(JsonFieldType.BOOLEAN)
                                        .description("주계좌여부")
                        )
                ));
    }


    @DisplayName("계좌 별칭 변경 API")
    @Test
    void editAccountTitle() throws Exception {

        EditAccountTitleRequest request = EditAccountTitleRequest.builder()
                .title("변경이다.")
                .build();

        AccountEditResponse response = AccountEditResponse.builder()
                .accountNumber("123456123")
                .balance(800000)
                .bankCode("088")
                .title("변경이다.")
                .mainAccount(true)
                .build();

        given(accountService.editTitle(any(EditAccountTitleDto.class)))
                .willReturn(response);

        mockMvc.perform(
                        patch("/account/title/1")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("edit-account-title",
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("변경할 별칭")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호"),
                                fieldWithPath("data.balance").type(JsonFieldType.NUMBER)
                                        .description("잔액"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행코드"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("별칭"),
                                fieldWithPath("data.mainAccount").type(JsonFieldType.BOOLEAN)
                                        .description("주계좌여부")

                        )
                ));
    }

    @DisplayName("주 계좌 변경 API")
    @Test
    void editAccountMainAccount() throws Exception {

        AccountEditResponse response = AccountEditResponse.builder()
                .accountNumber("123456123")
                .balance(800000)
                .bankCode("088")
                .title("주계좌가 될 양반.")
                .mainAccount(true)
                .build();

        given(accountService.editMainAccount(anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        patch("/account/main-account/{accountId}", 1L)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("edit-account-main",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호"),
                                fieldWithPath("data.balance").type(JsonFieldType.NUMBER)
                                        .description("잔액"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행코드"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("별칭"),
                                fieldWithPath("data.mainAccount").type(JsonFieldType.BOOLEAN)
                                        .description("주계좌여부")

                        )
                ));
    }

    @DisplayName("계좌 삭제 API")
    @Test
    void removeAccount() throws Exception {

        Boolean result = true;

        given(accountService.removeAccount(anyLong()))
                .willReturn(result);

        mockMvc.perform(
                        delete("/account/{accountId}", 1L)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("delete-account",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                        .description("삭제 결과: true 또는 false")
                        )
                ));
    }
}
