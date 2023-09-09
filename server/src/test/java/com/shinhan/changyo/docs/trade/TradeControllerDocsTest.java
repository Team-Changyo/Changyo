package com.shinhan.changyo.docs.trade;

import com.shinhan.changyo.api.controller.trade.TradeController;
import com.shinhan.changyo.api.controller.trade.request.CreateTradeRequest;
import com.shinhan.changyo.api.service.trade.TradeService;
import com.shinhan.changyo.api.service.trade.dto.CreateTradeDto;
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

public class TradeControllerDocsTest extends RestDocsSupport {

    private final TradeService tradeService = mock(TradeService.class);

    @Override
    protected Object initController() {
        return new TradeController(tradeService);
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
                .andExpect(status().isCreated())
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
}
