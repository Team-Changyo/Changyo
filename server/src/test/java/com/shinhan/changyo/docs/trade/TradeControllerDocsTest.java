package com.shinhan.changyo.docs.trade;

import com.shinhan.changyo.api.controller.trade.TradeController;
import com.shinhan.changyo.api.controller.trade.request.CreateTradeRequest;
import com.shinhan.changyo.api.controller.trade.request.ReturnDepositRequest;
import com.shinhan.changyo.api.controller.trade.request.ReturnRequest;
import com.shinhan.changyo.api.controller.trade.request.SimpleTradeRequest;
import com.shinhan.changyo.api.controller.trade.response.*;
import com.shinhan.changyo.api.service.trade.TradeQueryService;
import com.shinhan.changyo.api.service.trade.TradeService;
import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
import com.shinhan.changyo.api.service.trade.dto.DepositDetailDto;
import com.shinhan.changyo.api.service.trade.dto.SimpleTradeDto;
import com.shinhan.changyo.docs.RestDocsSupport;
import com.shinhan.changyo.domain.trade.TradeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
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

@WebMvcTest(TradeControllerDocsTest.class)
public class TradeControllerDocsTest extends RestDocsSupport {

    private final TradeService tradeService = mock(TradeService.class);
    private final TradeQueryService tradeQueryService = mock(TradeQueryService.class);

    @Override
    protected Object initController() {
        return new TradeController(tradeService, tradeQueryService);
    }

    @DisplayName("보증금 송금 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void createTrade() throws Exception {
        CreateTradeRequest request = CreateTradeRequest.builder()
                .accountId(1L)
                .qrCodeId(1L)
                .build();
        Long tradeId = 1L;

        given(tradeService.createTrade(any(CreateTradeDto.class), anyString()))
                .willReturn(tradeId);

        mockMvc.perform(
                        post("/trade")
                                .header("Authentication", "test")
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
                                        .description("보증금 QR 코드 식별키")
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

    @DisplayName("간편 송금 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void simpleTrade() throws Exception {
        SimpleTradeRequest request = SimpleTradeRequest.builder()
                .accountId(1L)
                .simpleQrCodeId(1L)
                .build();

        given(tradeService.simpleTrade(any(SimpleTradeDto.class), anyString()))
                .willReturn(true);

        mockMvc.perform(
                        post("/trade/simple")
                                .header("Authentication", "test")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("simple-trade",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("accountId").type(JsonFieldType.NUMBER)
                                        .description("출금 계좌 식별키"),
                                fieldWithPath("simpleQrCodeId").type(JsonFieldType.NUMBER)
                                        .description("간편 송금 QR 코드 식별키")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                        .description("송금 성공 여부")
                        )
                ));
    }

    @DisplayName("보증금 송금내역 반환대기 목록 조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getWaitingWithdrawalTrades() throws Exception {

        WaitWithdrawalDetailResponse detail1 = WaitWithdrawalDetailResponse.builder()
                .tradeId(1L)
                .qrCodeTitle("럭셔리 글램핑 객실이용")
                .memberName("전인혁")
                .amount(20000)
                .status(TradeStatus.WAIT)
                .build();
        WaitWithdrawalDetailResponse detail2 = WaitWithdrawalDetailResponse.builder()
                .tradeId(1L)
                .qrCodeTitle("럭셔리 글램핑 2호점 객실이용")
                .memberName("전인혁")
                .amount(30000)
                .status(TradeStatus.DONE)
                .build();
        List<WaitWithdrawalDetailResponse> details = List.of(detail1, detail2);
        WaitWithdrawalResponse response = WaitWithdrawalResponse.builder()
                .totalCount(2)
                .waitWithdrawals(details)
                .build();

        given(tradeQueryService.getWaitingWithdrawalTrades(anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/trade/withdrawal/wait")
                                .header("Authentication", "test")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-waiting-withdrawals",
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
                                        .description("전체 반환대기 건수"),
                                fieldWithPath("data.waitWithdrawals").type(JsonFieldType.ARRAY)
                                        .description("반환대기 거래내역리스트"),
                                fieldWithPath("data.waitWithdrawals[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("거래내역 식별키"),
                                fieldWithPath("data.waitWithdrawals[].qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("QR 코드 이름"),
                                fieldWithPath("data.waitWithdrawals[].memberName").type(JsonFieldType.STRING)
                                        .description("송금처"),
                                fieldWithPath("data.waitWithdrawals[].amount").type(JsonFieldType.NUMBER)
                                        .description("송금 금액"),
                                fieldWithPath("data.waitWithdrawals[].tradeDate").type(JsonFieldType.STRING)
                                        .description("반환일시")
                        )
                ));
    }

    @DisplayName("보증금 송금내역 반환완료 목록 조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getDoneWithdrawalTrades() throws Exception {

        DoneWithdrawalDetailResponse detail1 = DoneWithdrawalDetailResponse.builder()
                .tradeId(1L)
                .qrCodeTitle("럭셔리 글램핑 객실이용")
                .memberName("전인혁")
                .amount(20000)
                .tradeDate(LocalDateTime.of(2023, 8, 24, 14, 30))
                .build();
        DoneWithdrawalDetailResponse detail2 = DoneWithdrawalDetailResponse.builder()
                .tradeId(1L)
                .qrCodeTitle("럭셔리 글램핑 2호점 객실이용")
                .memberName("전인혁")
                .amount(30000)
                .tradeDate(LocalDateTime.of(2023, 8, 24, 14, 30))
                .build();
        List<DoneWithdrawalDetailResponse> details = List.of(detail1, detail2);
        DoneWithdrawalResponse response = DoneWithdrawalResponse.builder()
                .hasNextPage(false)
                .totalCount(2)
                .doneWithdrawals(details)
                .build();

        given(tradeQueryService.getDoneWithdrawalTrades(anyString(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/trade/withdrawal/done")
                                .header("Authentication", "test")
                                .param("lastTradeId", "2")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-done-withdrawals",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("lastTradeId")
                                        .description("마지막으로 조회된 거래내역 식별키")
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
                                fieldWithPath("data.hasNextPage").type(JsonFieldType.BOOLEAN)
                                        .description("다음 페이지 존재여부"),
                                fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER)
                                        .description("전체 반환완료 건수"),
                                fieldWithPath("data.doneWithdrawals").type(JsonFieldType.ARRAY)
                                        .description("반환완료 거래내역리스트"),
                                fieldWithPath("data.doneWithdrawals[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("거래내역 식별키"),
                                fieldWithPath("data.doneWithdrawals[].qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("QR 코드 이름"),
                                fieldWithPath("data.doneWithdrawals[].memberName").type(JsonFieldType.STRING)
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
    @WithMockUser(roles = "MEMBER")
    void getDepositTrades() throws Exception {

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
                .totalCount(2)
                .depositOverviews(overviews)
                .build();

        given(tradeQueryService.getDepositTrades(anyString(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/trade/deposit")
                                .header("Authentication", "test")
                                .param("lastQrCodeId", "2")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-deposits",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("lastQrCodeId")
                                        .description("마지막으로 조회된 QR 코드 식별키")
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
                                fieldWithPath("data.hasNextPage").type(JsonFieldType.BOOLEAN)
                                        .description("다음 페이지 존재여부"),
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
    @WithMockUser(roles = "MEMBER")
    void getDepositTradesDetail() throws Exception {
        String qrCodeId = "1";

        DepositDetailDto detail1 = DepositDetailDto.builder()
                .tradeId(2L)
                .status(TradeStatus.WAIT)
                .memberName("홍진식")
                .tradeDate(LocalDateTime.of(2023, 8, 24, 14, 30))
                .build();

        DepositDetailDto detail2 = DepositDetailDto.builder()
                .tradeId(1L)
                .status(TradeStatus.DONE)
                .memberName("임우택")
                .tradeDate(LocalDateTime.of(2023, 8, 21, 10, 35))
                .build();

        DepositDetailResponse response = DepositDetailResponse.builder()
                .qrCodeTitle("럭셔리 글램핑 객실이용")
                .amount(20000)
                .totalAmount(40000)
                .waitCount(1)
                .doneCount(1)
                .waitDetails(List.of(detail1))
                .doneDetails(List.of(detail2))
                .build();

        given(tradeQueryService.getDepositDetails(anyLong(), anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/trade/deposit/detail")
                                .header("Authentication", "test")
                                .param("qrCodeId", qrCodeId)
                                .param("lastTradeId", "2")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-deposit-details",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("qrCodeId")
                                        .description("보증금 정산관리 식별키"),
                                parameterWithName("lastTradeId")
                                        .description("마지막으로 조회된 거래내역 식별키")
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
                                fieldWithPath("data.hasNextPage").type(JsonFieldType.BOOLEAN)
                                        .description("다음 페이지 존재여부"),
                                fieldWithPath("data.qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("보증금 정산관리 이름"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("입금 단위"),
                                fieldWithPath("data.totalAmount").type(JsonFieldType.NUMBER)
                                        .description("총액"),
                                fieldWithPath("data.waitCount").type(JsonFieldType.NUMBER)
                                        .description("반환대기 건수"),
                                fieldWithPath("data.doneCount").type(JsonFieldType.NUMBER)
                                        .description("반환완료 건수"),
                                fieldWithPath("data.waitDetails").type(JsonFieldType.ARRAY)
                                        .description("보증금 반환대기 상세목록"),
                                fieldWithPath("data.waitDetails[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 거래내역 식별키"),
                                fieldWithPath("data.waitDetails[].status").type(JsonFieldType.STRING)
                                        .description("거래상태"),
                                fieldWithPath("data.waitDetails[].memberName").type(JsonFieldType.STRING)
                                        .description("입금자명"),
                                fieldWithPath("data.waitDetails[].tradeDate").type(JsonFieldType.STRING)
                                        .description("입금일시"),
                                fieldWithPath("data.doneDetails").type(JsonFieldType.ARRAY)
                                        .description("보증금 반환완료 상세목록"),
                                fieldWithPath("data.doneDetails[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 거래내역 식별키"),
                                fieldWithPath("data.doneDetails[].status").type(JsonFieldType.STRING)
                                        .description("거래상태"),
                                fieldWithPath("data.doneDetails[].memberName").type(JsonFieldType.STRING)
                                        .description("입금자명"),
                                fieldWithPath("data.doneDetails[].tradeDate").type(JsonFieldType.STRING)
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

        ReturnRequest request = ReturnRequest.builder()
                .returnRequests(requests)
                .build();

        given(tradeService.returnDeposits(anyList()))
                .willReturn(true);

        mockMvc.perform(
                        post("/trade/deposit")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("return-deposit",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("returnRequests[].tradeId").type(JsonFieldType.NUMBER)
                                        .description("보증금 거래내역 식별키"),
                                fieldWithPath("returnRequests[].amount").type(JsonFieldType.NUMBER)
                                        .description("보증금 송금 금액"),
                                fieldWithPath("returnRequests[].fee").type(JsonFieldType.NUMBER)
                                        .description("수수료"),
                                fieldWithPath("returnRequests[].reason").type(JsonFieldType.STRING)
                                        .description("반환 사유"),
                                fieldWithPath("returnRequests[].description").type(JsonFieldType.STRING)
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
