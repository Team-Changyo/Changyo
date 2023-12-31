package com.shinhan.changyo.docs.transfer;

import com.shinhan.changyo.api.controller.transfer.TransferController;
import com.shinhan.changyo.api.controller.transfer.response.*;
import com.shinhan.changyo.api.service.transfer.TransferQueryService;
import com.shinhan.changyo.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferControllerDocsTest.class)
public class TransferControllerDocsTest extends RestDocsSupport {

    private final TransferQueryService transferQueryService = mock(TransferQueryService.class);

    @Override
    protected Object initController() {
        return new TransferController(transferQueryService);
    }

    @DisplayName("보증금 송금 정보 조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getTransferInfo() throws Exception {
        Long qrCodeId = 1L;
        Long accountId = 1L;

        ClientAccountResponse clientAccount = ClientAccountResponse.builder()
                .accountId(accountId)
                .bankCode("088")
                .accountNumber("110291999999")
                .productName("입출금통장")
                .memberName("홍진식")
                .balance(980000)
                .build();

        StoreAccountResponse storeAccount = StoreAccountResponse.builder()
                .qrCodeId(qrCodeId)
                .qrCodeTitle("럭셔리 글램핑 객실이용")
                .amount(20000)
                .bankCode("088")
                .accountNumber("110184999999")
                .productName("입출금통장")
                .memberName("김신한")
                .build();
        TransferInfoResponse response = TransferInfoResponse.of(storeAccount, clientAccount);

        given(transferQueryService.getTransferInfo(anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/transfer")
                                .header("Authentication", "test")
                                .param("qrCodeId", "1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-transfer",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("qrCodeId")
                                        .description("보증금 송금 QR 코드 식별키")
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
                                fieldWithPath("data.storeAccount").type(JsonFieldType.OBJECT)
                                        .description("입금 계좌 정보"),
                                fieldWithPath("data.storeAccount.qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR 코드 식별키"),
                                fieldWithPath("data.storeAccount.qrCodeTitle").type(JsonFieldType.STRING)
                                        .description("QR 코드 이름"),
                                fieldWithPath("data.storeAccount.amount").type(JsonFieldType.NUMBER)
                                        .description("입금 단위"),
                                fieldWithPath("data.storeAccount.bankCode").type(JsonFieldType.STRING)
                                        .description("입금 은행코드"),
                                fieldWithPath("data.storeAccount.accountNumber").type(JsonFieldType.STRING)
                                        .description("입금 계좌 계좌번호"),
                                fieldWithPath("data.storeAccount.productName").type(JsonFieldType.STRING)
                                        .description("입금 계좌 상품명"),
                                fieldWithPath("data.storeAccount.memberName").type(JsonFieldType.STRING)
                                        .description("입금 계좌 회원명"),
                                fieldWithPath("data.clientAccount").type(JsonFieldType.OBJECT)
                                        .description("출금 계좌 정보"),
                                fieldWithPath("data.clientAccount.accountId").type(JsonFieldType.NUMBER)
                                        .description("출금 계좌 식별키"),
                                fieldWithPath("data.clientAccount.bankCode").type(JsonFieldType.STRING)
                                        .description("출금 은행코드"),
                                fieldWithPath("data.clientAccount.accountNumber").type(JsonFieldType.STRING)
                                        .description("출금 계좌 계좌번호"),
                                fieldWithPath("data.clientAccount.productName").type(JsonFieldType.STRING)
                                        .description("출금 계좌 상품명"),
                                fieldWithPath("data.clientAccount.memberName").type(JsonFieldType.STRING)
                                        .description("출금 계좌 회원명"),
                                fieldWithPath("data.clientAccount.balance").type(JsonFieldType.NUMBER)
                                        .description("출금 계좌 잔액")
                        )
                ));
    }

    @DisplayName("보증금 송금 정보 조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getSimpleTransferInfo() throws Exception {
        Long qrCodeId = 1L;
        Long accountId = 1L;

        ClientAccountResponse clientAccount = ClientAccountResponse.builder()
                .accountId(accountId)
                .bankCode("088")
                .accountNumber("110184999999")
                .productName("입출금통장")
                .memberName("김신한")
                .balance(980000)
                .build();

        SimpleStoreAccountResponse storeAccount = SimpleStoreAccountResponse.builder()
                .simpleQrCodeId(qrCodeId)
                .memberName("홍진식")
                .amount(20000)
                .bankCode("088")
                .accountNumber("110290999999")
                .build();
        SimpleTransferInfoResponse response = SimpleTransferInfoResponse.of(storeAccount, clientAccount);

        given(transferQueryService.getSimpleTransferInfo(anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/transfer/simple?simpleQrCodeId=1")
                                .header("Authentication", "test")
                                .param("simpleQrCodeId", "1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("search-simple-transfer",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("simpleQrCodeId")
                                        .description("간편 송금 QR 코드 식별키")
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
                                fieldWithPath("data.storeAccount").type(JsonFieldType.OBJECT)
                                        .description("입금 계좌 정보"),
                                fieldWithPath("data.storeAccount.simpleQrCodeId").type(JsonFieldType.NUMBER)
                                        .description("간편 송금 QR 코드 식별키"),
                                fieldWithPath("data.storeAccount.memberName").type(JsonFieldType.STRING)
                                        .description("입금 계좌 회원명"),
                                fieldWithPath("data.storeAccount.amount").type(JsonFieldType.NUMBER)
                                        .description("입금 단위"),
                                fieldWithPath("data.storeAccount.bankCode").type(JsonFieldType.STRING)
                                        .description("입금 은행코드"),
                                fieldWithPath("data.storeAccount.accountNumber").type(JsonFieldType.STRING)
                                        .description("입금 계좌 계좌번호"),
                                fieldWithPath("data.clientAccount").type(JsonFieldType.OBJECT)
                                        .description("출금 계좌 정보"),
                                fieldWithPath("data.clientAccount.accountId").type(JsonFieldType.NUMBER)
                                        .description("출금 계좌 식별키"),
                                fieldWithPath("data.clientAccount.bankCode").type(JsonFieldType.STRING)
                                        .description("출금 은행코드"),
                                fieldWithPath("data.clientAccount.accountNumber").type(JsonFieldType.STRING)
                                        .description("출금 계좌 계좌번호"),
                                fieldWithPath("data.clientAccount.productName").type(JsonFieldType.STRING)
                                        .description("출금 계좌 상품명"),
                                fieldWithPath("data.clientAccount.memberName").type(JsonFieldType.STRING)
                                        .description("출금 계좌 회원명"),
                                fieldWithPath("data.clientAccount.balance").type(JsonFieldType.NUMBER)
                                        .description("출금 계좌 잔액")
                        )
                ));
    }
}
