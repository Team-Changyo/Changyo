package com.shinhan.api.api.controller.trade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shinhan.api.ControllerTestSupport;
import com.shinhan.api.api.controller.account.AccountController;
import com.shinhan.api.api.controller.trade.request.TradeRequest;
import com.shinhan.api.api.controller.trade.response.TradeDetailResponse;
import com.shinhan.api.api.controller.trade.response.TradeResponse;
import com.shinhan.api.api.service.trade.TradeQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TradeController.class})
class TradeControllerTest extends ControllerTestSupport {

    @MockBean
    private TradeQueryService tradeQueryService;

    @DisplayName("거래 내역을 조회할 때 계좌 번호는 필수값이다.")
    @Test
    void getTradesWithoutAccountNumber() throws Exception {
        //given
        TradeRequest request = TradeRequest.builder()
            .build();

        //when //then
        mockMvc.perform(
                post("/v1/search/transaction")
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

    @DisplayName("거래 내역을 조회한다.")
    @Test
    void getTrades() throws Exception {
        //given
        TradeRequest request = TradeRequest.builder()
            .accountNumber("110184999999")
            .build();

        TradeDetailResponse detailResponse = TradeDetailResponse.builder()
            .tradeDate("20230205")
            .tradeTime("222828")
            .summary("신한체크")
            .withdrawalAmount(4700)
            .depositAmount(0)
            .content("스타벅스")
            .balance(11341147)
            .status(2)
            .dealershipName("신한")
            .build();

        TradeResponse response = TradeResponse.builder()
            .accountNumber("110184999999")
            .productName("저축예금")
            .balance(331551)
            .customerName("김신한")
            .tradeSize(1)
            .trades(List.of(detailResponse))
            .build();

        given(tradeQueryService.getTrade(anyString()))
            .willReturn(response);

        //when //then
        mockMvc.perform(
                post("/v1/search/transaction")
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