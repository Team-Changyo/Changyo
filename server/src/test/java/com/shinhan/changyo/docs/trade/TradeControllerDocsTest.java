package com.shinhan.changyo.docs.trade;

import com.shinhan.changyo.api.controller.trade.TradeController;
import com.shinhan.changyo.api.controller.trade.request.CreateTradeRequest;
import com.shinhan.changyo.api.controller.trade.request.ReturnDepositRequest;
import com.shinhan.changyo.api.controller.trade.response.*;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
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
                .withdrawalAccountNumber("1102008999999")
                .qrCodeId(1L)
                .depositAccountNumber("110054999999")
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
                                fieldWithPath("withdrawalAccountNumber").type(JsonFieldType.STRING)
                                                .description("출금 계좌 번호"),
                                fieldWithPath("qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 QR 코드 식별키"),
                                fieldWithPath("depositAccountNumber").type(JsonFieldType.STRING)
                                        .description("입금 계좌 번호"),
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
                .tradeDate(TradeStatus.WAIT.getText())
                .build();
        WithdrawalDetailResponse doneDetail1 = WithdrawalDetailResponse.builder()
                .tradeId(1L)
                .qrCodeTitle("럭셔리 글램핑 2호점 객실이용")
                .depositName("전인혁")
                .amount(30000)
                .tradeDate("2023-03-08 19:30")
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
                        get("/trade/withdrawal")
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
                                fieldWithPath("data.waitWithdrawals[].tradeDate").type(JsonFieldType.STRING)
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
                                fieldWithPath("data.doneWithdrawals[].tradeDate").type(JsonFieldType.STRING)
                                        .description("반환일시")
                        )
                ));
    }

    @DisplayName("보증금 정산관리 목록 조회 API")
    @Test
    void getDepositTrades() throws Exception {
        Long memberId = 1L;

        DepositOverviewResponse overview1 = DepositOverviewResponse.builder()
                .qrCodeId(1L)
                .qrCodeTitle("럭셔리 글램핑 객실이용")
                .amount(20000)
                .remainTotal(60000)
                .remainCount(3)
                .build();
        DepositOverviewResponse overview2 = DepositOverviewResponse.builder()
                .qrCodeId(2L)
                .qrCodeTitle("럭셔리 글램핑 2호점 객실이용")
                .amount(30000)
                .remainTotal(0)
                .remainCount(0)
                .build();

        List<DepositOverviewResponse> overviews = List.of(overview1, overview2);

        DepositResponse response = DepositResponse.builder()
                .totalCount(1)
                .depositOverviews(overviews)
                .build();

        given(tradeQueryService.getDepositTrades(anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/trade/deposit")
                                .header("memberId", String.valueOf(memberId))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-deposits",
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
                                fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER)
                                        .description("보증금 정산관리 건수"),
                                fieldWithPath("data.depositOverviews").type(JsonFieldType.ARRAY)
                                        .description("보증금 정산관리 개요 목록"),
                                fieldWithPath("data.depositOverviews[].qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 정산관리 식별키"),
                                fieldWithPath("data.depositOverviews[].qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("보증금 정산관리 이름"),
                                fieldWithPath("data.depositOverviews[].amount").type(JsonFieldType.NUMBER)
                                        .description("보증금 입금단위"),
                                fieldWithPath("data.depositOverviews[].remainTotal").type(JsonFieldType.NUMBER)
                                        .description("보증금 미반환 총액"),
                                fieldWithPath("data.depositOverviews[].remainCount").type(JsonFieldType.NUMBER)
                                        .description("보증금 미반환 건수")
                        )
                ));
    }

    @DisplayName("보증금 정산관리 상세조회 API")
    @Test
    void getDepositTradesDetail() throws Exception {
        String qrCodeId = "1";
        String status = "반환 전";

        DepositDetail detail2 = DepositDetail.builder()
                .tradeId(1L)
                .withdrawalName("임우택")
                .tradeDate("2023-08-21 10:35")
                .build();

        DepositDetail detail1 = DepositDetail.builder()
                .tradeId(2L)
                .withdrawalName("홍진식")
                .tradeDate("2023-08-24 14:30")
                .build();
        List<DepositDetail> details = List.of(detail1, detail2);

        DepositDetailResponse response = DepositDetailResponse.builder()
                .qrCodeTitle("럭셔리 글램핑 객실이용")
                .amount(20000)
                .remainTotal(40000)
                .remainCount(2)
                .depositDetails(details)
                .build();

        given(tradeQueryService.getDepositDetails(anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/trade/deposit/detail")
                                .param("qrCodeId", qrCodeId)
                                .param("status", status)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-deposit-details",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("qrCodeId")
                                        .description("보증금 정산관리 식별키"),
                                parameterWithName("status")
                                        .description("거래 상태")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("보증금 정산관리 이름"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("입금 단위"),
                                fieldWithPath("data.remainTotal").type(JsonFieldType.NUMBER)
                                        .description("미반환 총액"),
                                fieldWithPath("data.remainCount").type(JsonFieldType.NUMBER)
                                        .description("미반환 건수"),
                                fieldWithPath("data.depositDetails").type(JsonFieldType.ARRAY)
                                        .description("보증금 정산관리 상세목록"),
                                fieldWithPath("data.depositDetails[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 거래내역 식별키"),
                                fieldWithPath("data.depositDetails[].withdrawalName").type(JsonFieldType.STRING)
                                        .description("입금자명"),
                                fieldWithPath("data.depositDetails[].tradeDate").type(JsonFieldType.STRING)
                                        .description("입금일시")
                        )
                ));
    }

    @DisplayName("보증금 반환 API")
    @Test
    void returnDeposit() throws Exception {
        ReturnDepositRequest request1 = ReturnDepositRequest.builder()
                .tradeId(1L)
                .amount(20000)
                .fee(6000)
                .reason("물품/기물 파손")
                .description("침대 파손")
                .build();

        ReturnDepositRequest request2 = ReturnDepositRequest.builder()
                .tradeId(2L)
                .amount(20000)
                .reason("")
                .description("")
                .build();

        List<ReturnDepositRequest> requests = List.of(request1, request2);

        given(tradeService.returnDeposit(anyList()))
                .willReturn(true);

        mockMvc.perform(
                        post("/trade/deposit")
                                .content(objectMapper.writeValueAsString(requests))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("return-deposit",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 거래내역 식별키"),
                                fieldWithPath("[].amount").type(JsonFieldType.NUMBER)
                                        .description("보증금 송금 금액"),
                                fieldWithPath("[].fee").type(JsonFieldType.NUMBER)
                                        .description("수수료"),
                                fieldWithPath("[].reason").type(JsonFieldType.STRING)
                                        .description("반환 사유"),
                                fieldWithPath("[].description").type(JsonFieldType.STRING)
                                        .description("반환사유 상세")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                        .description("반환 성공 여부")
                        )
                ));
    }
}
