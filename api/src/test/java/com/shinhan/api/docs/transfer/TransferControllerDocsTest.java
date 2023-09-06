package com.shinhan.api.docs.transfer;

import com.shinhan.api.api.controller.transfer.TransferController;
import com.shinhan.api.api.controller.transfer.request.OneTransferRequest;
import com.shinhan.api.api.controller.transfer.request.TransferRequest;
import com.shinhan.api.api.controller.transfer.response.OneTransferResponse;
import com.shinhan.api.api.controller.transfer.response.TransferResponse;
import com.shinhan.api.api.service.transfer.TransferQueryService;
import com.shinhan.api.api.service.transfer.dto.OneTransferDto;
import com.shinhan.api.api.service.transfer.dto.TransferDto;
import com.shinhan.api.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
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

public class TransferControllerDocsTest extends RestDocsSupport {

    private final TransferQueryService transferQueryService = mock(TransferQueryService.class);

    @Override
    protected Object initController() {
        return new TransferController(transferQueryService);
    }

    @DisplayName("이체(원화) API")
    @Test
    void transfer() throws Exception {
        TransferRequest request = TransferRequest.builder()
            .withdrawalAccountNumber("1102008999999")
            .depositBankCode("088")
            .depositAccountNumber("110054999999")
            .amount(30000)
            .depositMemo("김신한")
            .withdrawalMemo("회비")
            .build();

        TransferResponse response = TransferResponse.builder()
            .withdrawalAccountNumber("110200899999")
            .depositBankCode("088")
            .depositAccountNumber("110054999999")
            .amount(30000)
            .depositMemo("김신한")
            .withdrawalMemo("회비")
            .result(3542000)
            .build();

        given(transferQueryService.transfer(any(TransferDto.class)))
            .willReturn(response);

        mockMvc.perform(
                post("/v1/transfer/krw")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("transfer",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("withdrawalAccountNumber").type(JsonFieldType.STRING)
                        .description("출금계좌번호"),
                    fieldWithPath("depositBankCode").type(JsonFieldType.STRING)
                        .description("입금은행코드"),
                    fieldWithPath("depositAccountNumber").type(JsonFieldType.STRING)
                        .description("입금계좌번호"),
                    fieldWithPath("amount").type(JsonFieldType.NUMBER)
                        .description("이체금액"),
                    fieldWithPath("depositMemo").type(JsonFieldType.STRING)
                        .description("입금계좌통장메모"),
                    fieldWithPath("withdrawalMemo").type(JsonFieldType.STRING)
                        .description("출금계좌통장메모")
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
                    fieldWithPath("data.withdrawalAccountNumber").type(JsonFieldType.STRING)
                        .description("출금계좌번호"),
                    fieldWithPath("data.depositBankCode").type(JsonFieldType.STRING)
                        .description("입금은행코드"),
                    fieldWithPath("data.depositAccountNumber").type(JsonFieldType.STRING)
                        .description("입급계좌번호"),
                    fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                        .description("이체금액"),
                    fieldWithPath("data.depositMemo").type(JsonFieldType.STRING)
                        .description("입금계좌통장메모"),
                    fieldWithPath("data.withdrawalMemo").type(JsonFieldType.STRING)
                        .description("출금계좌통장메모"),
                    fieldWithPath("data.result").type(JsonFieldType.NUMBER)
                        .description("거래후잔액")
                )
            ));
    }

    @DisplayName("1원 이체 API")
    @Test
    void oneTransfer() throws Exception {
        OneTransferRequest request = OneTransferRequest.builder()
            .bankCode("088")
            .accountNumber("110222999999")
            .memo("1234 SSAFY")
            .build();

        OneTransferResponse response = OneTransferResponse.builder()
            .bankCode("088")
            .accountNumber("110222999999")
            .build();

        given(transferQueryService.oneTransfer(any(OneTransferDto.class)))
            .willReturn(response);

        mockMvc.perform(
                post("/v1/auth/1transfer")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("transfer-one",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("bankCode").type(JsonFieldType.STRING)
                        .description("입금은행코드"),
                    fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                        .description("입금계좌번호"),
                    fieldWithPath("memo").type(JsonFieldType.STRING)
                        .description("입금통장메모")
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
                    fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                        .description("입금은행코드"),
                    fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                        .description("입금계좌번호")
                )
            ));
    }
}
