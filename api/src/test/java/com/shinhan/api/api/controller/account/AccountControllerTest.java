package com.shinhan.api.api.controller.account;

import com.shinhan.api.ControllerTestSupport;
import com.shinhan.api.api.controller.account.request.AccountRequest;
import com.shinhan.api.api.controller.account.request.CustomerNameRequest;
import com.shinhan.api.api.controller.account.response.AccountDetailResponse;
import com.shinhan.api.api.controller.account.response.AccountResponse;
import com.shinhan.api.api.controller.account.response.CustomerNameResponse;
import com.shinhan.api.api.controller.trade.TradeController;
import com.shinhan.api.api.service.account.AccountQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {AccountController.class})
class AccountControllerTest extends ControllerTestSupport {

    @MockBean
    private AccountQueryService accountQueryService;

    @DisplayName("계좌 정보를 조회할 때 계좌 번호는 필수값이다.")
    @Test
    void getAccountDetailWithoutAccountNumber() throws Exception {
        //given
        AccountRequest request = AccountRequest.builder()
                .build();

        //when //then
        mockMvc.perform(
                        post("/v1/account")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("계좌 번호는 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("계좌 잔액을 조회할 때 계좌 번호는 필수값이다.")
    @Test
    void getAccountBalanceWithoutAccountNumber() throws Exception {
        //given
        AccountRequest request = AccountRequest.builder()
            .build();

        //when //then
        mockMvc.perform(
                post("/v1/account/balance/detail")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("계좌 번호는 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("계좌 정보를 조회한다.")
    @Test
    void getAccountDetail() throws Exception {
        //given
        AccountRequest request = AccountRequest.builder()
                .accountNumber("110184999999")
                .build();

        AccountDetailResponse response = AccountDetailResponse.builder()
                .balance(10_000_000)
                .productName("예금")
                .customerName("홍진식")
                .build();

        given(accountQueryService.getAccountDetail(anyString()))
                .willReturn(response);

        //when //then
        mockMvc.perform(
                        post("/v1/account")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }


    @DisplayName("계좌 잔액을 조회한다.")
    @Test
    void getAccountBalance() throws Exception {
        //given
        AccountRequest request = AccountRequest.builder()
            .accountNumber("110184999999")
            .build();

        AccountResponse response = AccountResponse.builder()
            .accountNumber("110184999999")
            .balance(10_000_000)
            .build();

        given(accountQueryService.getAccountBalance(anyString()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                post("/v1/account/balance/detail")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("SUCCESS"))
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @DisplayName("예금주 실명을 조회할 때 은행 코드는 필수값이다.")
    @Test
    void getCustomerNameWithoutBankCode() throws Exception {
        //given
        CustomerNameRequest request = CustomerNameRequest.builder()
            .accountNumber("110184999999")
            .build();

        //when //then
        mockMvc.perform(
                post("/v1/search/name")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("은행 코드는 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("예금주 실명을 조회할 때 계좌 번호는 필수값이다.")
    @Test
    void getCustomerNameWithoutAccountNumber() throws Exception {
        //given
        CustomerNameRequest request = CustomerNameRequest.builder()
            .bankCode("088")
            .build();

        //when //then
        mockMvc.perform(
                post("/v1/search/name")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("계좌 번호는 필수입니다."))
            .andExpect(jsonPath("$.data").isEmpty());
    }

    @DisplayName("예금주 실명을 조회한다.")
    @Test
    void getCustomerName() throws Exception {
        //given
        CustomerNameRequest request = CustomerNameRequest.builder()
            .bankCode("088")
            .accountNumber("110184999999")
            .build();

        CustomerNameResponse response = CustomerNameResponse.builder()
            .bankCode("088")
            .accountNumber("110184999999")
            .customerName("김신한")
            .build();

        given(accountQueryService.getCustomerName(anyString(), anyString()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                post("/v1/search/name")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.message").value("SUCCESS"))
            .andExpect(jsonPath("$.data").isNotEmpty());
    }
}