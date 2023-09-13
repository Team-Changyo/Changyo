package com.shinhan.changyo.docs.account;

import com.shinhan.changyo.api.controller.account.AccountController;
import com.shinhan.changyo.api.controller.account.request.CreateAccountRequest;
import com.shinhan.changyo.api.controller.account.request.EditAccountTitleRequest;
import com.shinhan.changyo.api.controller.account.response.*;
import com.shinhan.changyo.api.service.account.AccountQueryService;
import com.shinhan.changyo.api.service.account.AccountService;
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
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
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
                .bankCode("088")
                .accountNumber("110184999999")
                .title("싸피월급통장")
                .mainAccount(true)
                .build();

        Long accountId = 1L;

        given(accountService.createAccount(any(CreateAccountRequest.class), anyString()))
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
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("은행코드"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호"),
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
    @WithMockUser(roles = "MEMBER")
    void getAccounts() throws Exception {
        Long memberId = 1L;

        AccountDetailResponse account1 = AccountDetailResponse.builder()
                .accountId(1l)
                .accountNumber("110184999999")
                .balance(200501)
                .bankCode("088")
                .mainAccount(true)
                .build();

        AccountDetailResponse account2 = AccountDetailResponse.builder()
                .accountId(2l)
                .accountNumber("110185999999")
                .balance(0)
                .bankCode("088")
                .mainAccount(false)
                .build();

        List<AccountDetailResponse> accounts = List.of(account1, account2);
        List<String> bankCodeList = List.of("088");

        AccountResponse response = AccountResponse.builder()
                .accountSize(accounts.size())
                .accountTotalBalance(200501)
                .bankCodeList(bankCodeList)
                .accountDetailResponses(accounts)
                .build();

        given(accountQueryService.getAccounts(anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/account")
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
                                fieldWithPath("data.accountTotalBalance").type(JsonFieldType.NUMBER)
                                        .description("전체 계좌 금액"),
                                fieldWithPath("data.bankCodeList").type(JsonFieldType.ARRAY)
                                        .description("계좌 은행 코드 정보"),
                                fieldWithPath("data.accountDetailResponses").type(JsonFieldType.ARRAY)
                                        .description("계좌 정보 데이터"),
                                fieldWithPath("data.accountDetailResponses[].accountId").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별 키"),
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

    @DisplayName("계좌 거래내역 상세조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getAccountDetailAll() throws Exception {
        Long accountId = 1L;

        AllTradeResponse allTradeResponses1 = AllTradeResponse.builder()
                .tradeDate("20230220")
                .tradeTime("023753")
                .content("전세보증금")
                .balance(341147)
                .withdrawalAmount(11000000)
                .depositAmount(0)
                .status(2)
                .build();

        AllTradeResponse allTradeResponses2 = AllTradeResponse.builder()
                .tradeDate("20230220")
                .tradeTime("023754")
                .content("김밥천국")
                .balance(341147)
                .withdrawalAmount(0)
                .depositAmount(5000)
                .status(1)
                .build();

        List<AllTradeResponse> tmp = List.of(allTradeResponses1, allTradeResponses2);
        Map<String, List<AllTradeResponse>> allTradeResponses = Map.of("20230220", tmp);


        AccountTradeAllResponse response = AccountTradeAllResponse.builder()
                .accountId(1L)
                .accountNumber("12345612")
                .balance(341147)
                .bankCode("088")
                .allTradeResponses(allTradeResponses)

                .build();

        given(accountQueryService.getAccountTradeAll(anyString(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/account/1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-account-detail-all",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.accountId").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별 키"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.balance").type(JsonFieldType.NUMBER)
                                        .description("잔액"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("data.allTradeResponses").type(JsonFieldType.OBJECT)
                                        .description("거래 날짜 별 거래내역 List를 담은 Map"),
                                fieldWithPath("data.allTradeResponses.*").type(JsonFieldType.ARRAY)
                                        .description("객체 목록"),
                                fieldWithPath("data.allTradeResponses.*.[].tradeDate").type(JsonFieldType.STRING)
                                        .description("거래 날짜"),
                                fieldWithPath("data.allTradeResponses.*.[].tradeTime").type(JsonFieldType.STRING)
                                        .description("거래 시간"),
                                fieldWithPath("data.allTradeResponses.*.[].content").type(JsonFieldType.STRING)
                                        .description("(입금처/출금처)"),
                                fieldWithPath("data.allTradeResponses.*.[].balance").type(JsonFieldType.NUMBER)
                                        .description("거래 후 잔액"),
                                fieldWithPath("data.allTradeResponses.*.[].withdrawalAmount").type(JsonFieldType.NUMBER)
                                        .description("출금 금액"),
                                fieldWithPath("data.allTradeResponses.*.[].depositAmount").type(JsonFieldType.NUMBER)
                                        .description("입금 금액"),
                                fieldWithPath("data.allTradeResponses.*.[].status").type(JsonFieldType.NUMBER)
                                        .description("입지 구분 / 1 : 입금 , 2 : 출금")
                        )
                ));


    }

    @DisplayName("입금 계좌 거래내역 상세조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getAccountDetailDeposit() throws Exception {
        Long accountId = 1L;

        AllTradeResponse allTradeResponses1 = AllTradeResponse.builder()
                .tradeDate("20230220")
                .tradeTime("023753")
                .content("전세보증금")
                .balance(341147)
                .withdrawalAmount(11000000)
                .depositAmount(0)
                .status(1)
                .build();

        AllTradeResponse allTradeResponses2 = AllTradeResponse.builder()
                .tradeDate("20230220")
                .tradeTime("023754")
                .content("김밥천국")
                .balance(341147)
                .withdrawalAmount(0)
                .depositAmount(5000)
                .status(1)
                .build();

        List<AllTradeResponse> tmp = List.of(allTradeResponses1, allTradeResponses2);
        Map<String, List<AllTradeResponse>> allTradeResponses = Map.of("20230220", tmp);


        AccountTradeAllResponse response = AccountTradeAllResponse.builder()
                .accountId(1L)
                .accountNumber("12345612")
                .balance(341147)
                .bankCode("088")
                .allTradeResponses(allTradeResponses)

                .build();

        given(accountQueryService.getAccountTradeDeposit(anyString(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/account/deposit/1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-account-detail-deposit",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.accountId").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별 키"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.balance").type(JsonFieldType.NUMBER)
                                        .description("잔액"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("data.allTradeResponses").type(JsonFieldType.OBJECT)
                                        .description("거래 날짜 별 거래내역 List를 담은 Map"),
                                fieldWithPath("data.allTradeResponses.*").type(JsonFieldType.ARRAY)
                                        .description("객체 목록"),
                                fieldWithPath("data.allTradeResponses.*.[].tradeDate").type(JsonFieldType.STRING)
                                        .description("거래 날짜"),
                                fieldWithPath("data.allTradeResponses.*.[].tradeTime").type(JsonFieldType.STRING)
                                        .description("거래 시간"),
                                fieldWithPath("data.allTradeResponses.*.[].content").type(JsonFieldType.STRING)
                                        .description("(입금처/출금처)"),
                                fieldWithPath("data.allTradeResponses.*.[].balance").type(JsonFieldType.NUMBER)
                                        .description("거래 후 잔액"),
                                fieldWithPath("data.allTradeResponses.*.[].withdrawalAmount").type(JsonFieldType.NUMBER)
                                        .description("출금 금액"),
                                fieldWithPath("data.allTradeResponses.*.[].depositAmount").type(JsonFieldType.NUMBER)
                                        .description("입금 금액"),
                                fieldWithPath("data.allTradeResponses.*.[].status").type(JsonFieldType.NUMBER)
                                        .description("입지 구분 / 1 : 입금 , 2 : 출금")
                        )
                ));


    }


    @DisplayName("출금 계좌 거래내역 상세조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getAccountDetailWithdrawal() throws Exception {
        Long accountId = 1L;

        AllTradeResponse allTradeResponses1 = AllTradeResponse.builder()
                .tradeDate("20230220")
                .tradeTime("023753")
                .content("전세보증금")
                .balance(341147)
                .withdrawalAmount(11000000)
                .depositAmount(0)
                .status(2)
                .build();

        AllTradeResponse allTradeResponses2 = AllTradeResponse.builder()
                .tradeDate("20230220")
                .tradeTime("023754")
                .content("김밥천국")
                .balance(341147)
                .withdrawalAmount(0)
                .depositAmount(5000)
                .status(2)
                .build();

        List<AllTradeResponse> tmp = List.of(allTradeResponses1, allTradeResponses2);
        Map<String, List<AllTradeResponse>> allTradeResponses = Map.of("20230220", tmp);


        AccountTradeAllResponse response = AccountTradeAllResponse.builder()
                .accountId(1L)
                .accountNumber("12345612")
                .balance(341147)
                .bankCode("088")
                .allTradeResponses(allTradeResponses)

                .build();

        given(accountQueryService.getAccountTradeWithdrawal(anyString(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/account/withdrawal/1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-account-detail-withdrawal",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.accountId").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별 키"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.balance").type(JsonFieldType.NUMBER)
                                        .description("잔액"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("data.allTradeResponses").type(JsonFieldType.OBJECT)
                                        .description("거래 날짜 별 거래내역 List를 담은 Map"),
                                fieldWithPath("data.allTradeResponses.*").type(JsonFieldType.ARRAY)
                                        .description("객체 목록"),
                                fieldWithPath("data.allTradeResponses.*.[].tradeDate").type(JsonFieldType.STRING)
                                        .description("거래 날짜"),
                                fieldWithPath("data.allTradeResponses.*.[].tradeTime").type(JsonFieldType.STRING)
                                        .description("거래 시간"),
                                fieldWithPath("data.allTradeResponses.*.[].content").type(JsonFieldType.STRING)
                                        .description("(입금처/출금처)"),
                                fieldWithPath("data.allTradeResponses.*.[].balance").type(JsonFieldType.NUMBER)
                                        .description("거래 후 잔액"),
                                fieldWithPath("data.allTradeResponses.*.[].withdrawalAmount").type(JsonFieldType.NUMBER)
                                        .description("출금 금액"),
                                fieldWithPath("data.allTradeResponses.*.[].depositAmount").type(JsonFieldType.NUMBER)
                                        .description("입금 금액"),
                                fieldWithPath("data.allTradeResponses.*.[].status").type(JsonFieldType.NUMBER)
                                        .description("입지 구분 / 1 : 입금 , 2 : 출금")
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
