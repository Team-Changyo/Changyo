package com.shinhan.changyo.docs.trade;

import com.shinhan.changyo.api.controller.trade.TradeController;
import com.shinhan.changyo.api.controller.trade.request.CreateTradeRequest;
import com.shinhan.changyo.api.controller.trade.response.WithdrawalDetailResponse;
import com.shinhan.changyo.api.controller.trade.response.WithdrawalResponse;
import com.shinhan.changyo.api.service.trade.TradeQueryService;
import com.shinhan.changyo.api.service.trade.TradeService;
import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
import com.shinhan.changyo.docs.RestDocsSupport;
import com.shinhan.changyo.domain.trade.TradeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TradeControllerDocsTest extends RestDocsSupport {

    private final TradeService tradeService = mock(TradeService.class);
    private final TradeQueryService tradeQueryService = mock(TradeQueryService.class);

    @Override
    protected Object initController() {
        return new TradeController(tradeService, tradeQueryService);
    }

    @DisplayName("보증금 송금 API")
    @Test
    void createTrade() throws Exception {
        CreateTradeRequest request = CreateTradeRequest.builder()
                .accountId(1L)
                .qrCodeId(1L)
                .amount(20000)
                .content("럭셔리 글램핑 객실이용")
                .build();
        Long tradeId = 1L;

        given(tradeService.createTrade(any(CreateTradeDto.class)))
                .willReturn(tradeId);

        mockMvc.perform(
                        post("/trade")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("create-trade",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("accountId").type(JsonFieldType.NUMBER)
                                        .description("출금 계좌 식별키"),
                                fieldWithPath("qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 QR 코드 식별키"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("출금 계좌명")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("거래내역 식별키")
                        )
                ));
    }

    @DisplayName("보증금 송금내역 목록 조회 API")
    @Test
    void getWithdrawalTrades() throws Exception {
        Long memberId = 1L;

        WithdrawalDetailResponse waitDetail1 = WithdrawalDetailResponse.builder()
                .tradeId(1L)
                .qrCodeTitle("럭셔리 글램핑 객실이용")
                .depositName("전인혁")
                .amount(20000)
                .returnDate(TradeStatus.WAIT.getText())
                .build();
        WithdrawalDetailResponse doneDetail1 = WithdrawalDetailResponse.builder()
                .tradeId(1L)
                .qrCodeTitle("럭셔리 글램핑 2호점 객실이용")
                .depositName("전인혁")
                .amount(30000)
                .returnDate("2023-03-08 19:30")
                .build();
        List<WithdrawalDetailResponse> waitDetails = List.of(waitDetail1);
        List<WithdrawalDetailResponse> doneDetails = List.of(doneDetail1);
        WithdrawalResponse response = WithdrawalResponse.builder()
                .remainCount(1)
                .finishCount(1)
                .waitWithdrawals(waitDetails)
                .doneWithdrawals(doneDetails)
                .build();

        given(tradeQueryService.getWithdrawalTrades(anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/trade")
                                .header("memberId", String.valueOf(memberId))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-withdrawals",
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
                                fieldWithPath("data.remainCount").type(JsonFieldType.NUMBER)
                                        .description("반환대기 건수"),
                                fieldWithPath("data.finishCount").type(JsonFieldType.NUMBER)
                                        .description("반환완료 건수"),
                                fieldWithPath("data.waitWithdrawals").type(JsonFieldType.ARRAY)
                                        .description("반환대기 거래내역리스트"),
                                fieldWithPath("data.waitWithdrawals[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("거래내역 식별키"),
                                fieldWithPath("data.waitWithdrawals[].qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("QR 코드 이름"),
                                fieldWithPath("data.waitWithdrawals[].depositName").type(JsonFieldType.STRING)
                                        .description("송금처"),
                                fieldWithPath("data.waitWithdrawals[].amount").type(JsonFieldType.NUMBER)
                                        .description("송금 금액"),
                                fieldWithPath("data.waitWithdrawals[].returnDate").type(JsonFieldType.STRING)
                                        .description("반환일시"),
                                fieldWithPath("data.doneWithdrawals").type(JsonFieldType.ARRAY)
                                        .description("반환완료 거래내역리스트"),
                                fieldWithPath("data.doneWithdrawals[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("거래내역 식별키"),
                                fieldWithPath("data.doneWithdrawals[].qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("QR 코드 이름"),
                                fieldWithPath("data.doneWithdrawals[].depositName").type(JsonFieldType.STRING)
                                        .description("송금처"),
                                fieldWithPath("data.doneWithdrawals[].amount").type(JsonFieldType.NUMBER)
                                        .description("송금 금액"),
                                fieldWithPath("data.doneWithdrawals[].returnDate").type(JsonFieldType.STRING)
                                        .description("반환일시")
                        )
                ));
    }
}
