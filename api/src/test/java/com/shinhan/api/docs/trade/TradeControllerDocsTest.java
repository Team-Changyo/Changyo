package com.shinhan.api.docs.trade;

import com.shinhan.api.api.controller.trade.TradeController;
import com.shinhan.api.api.controller.trade.request.TradeRequest;
import com.shinhan.api.api.controller.trade.response.TradeDetailResponse;
import com.shinhan.api.api.controller.trade.response.TradeResponse;
import com.shinhan.api.api.service.trade.TradeQueryService;
import com.shinhan.api.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Collections;

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

public class TradeControllerDocsTest extends RestDocsSupport {

    private final TradeQueryService tradeQueryService = mock(TradeQueryService.class);

    @Override
    protected Object initController() {
        return new TradeController(tradeQueryService);
    }

    @DisplayName("거래내역조회 API")
    @Test
    void getTrades() throws Exception {
        TradeRequest request = TradeRequest.builder()
            .accountNumber("110184999999")
            .build();

        TradeDetailResponse tradeDetailResponse = TradeDetailResponse.builder()
            .tradeDate("20230318")
            .tradeTime("154602")
            .summary("이자")
            .withdrawalAmount(0)
            .depositAmount(1404)
            .content("12.17~03.17")
            .balance(331551)
            .status("1")
            .dealershipName("영업부")
            .build();

        TradeResponse response = TradeResponse.builder()
            .accountNumber("110184999999")
            .productName("저축예금")
            .balance(331551)
            .customerName("김신한")
            .tradeSize(1)
            .trades(Collections.singletonList(tradeDetailResponse))
            .build();

        given(tradeQueryService.getTrade(anyString()))
            .willReturn(response);

        mockMvc.perform(
                post("/v1/search/transaction")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-trade",
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
                        .description("계좌번호"),
                    fieldWithPath("data.productName").type(JsonFieldType.STRING)
                        .description("상품명"),
                    fieldWithPath("data.balance").type(JsonFieldType.NUMBER)
                        .description("계좌잔액"),
                    fieldWithPath("data.customerName").type(JsonFieldType.STRING)
                        .description("고객명"),
                    fieldWithPath("data.tradeSize").type(JsonFieldType.NUMBER)
                        .description("거래내역반복횟수"),
                    fieldWithPath("data.trades").type(JsonFieldType.ARRAY)
                        .description("거래내역"),
                    fieldWithPath("data.trades[].tradeDate").type(JsonFieldType.STRING)
                        .description("거래일자"),
                    fieldWithPath("data.trades[].tradeTime").type(JsonFieldType.STRING)
                        .description("거래시간"),
                    fieldWithPath("data.trades[].summary").type(JsonFieldType.STRING)
                        .description("적요"),
                    fieldWithPath("data.trades[].withdrawalAmount").type(JsonFieldType.NUMBER)
                        .description("출금금액"),
                    fieldWithPath("data.trades[].depositAmount").type(JsonFieldType.NUMBER)
                        .description("입금금액"),
                    fieldWithPath("data.trades[].content").type(JsonFieldType.STRING)
                        .description("내용"),
                    fieldWithPath("data.trades[].balance").type(JsonFieldType.NUMBER)
                        .description("잔액"),
                    fieldWithPath("data.trades[].status").type(JsonFieldType.STRING)
                        .description("입지구분"),
                    fieldWithPath("data.trades[].dealershipName").type(JsonFieldType.STRING)
                        .description("거래점명")
                )
            ));
    }
}
