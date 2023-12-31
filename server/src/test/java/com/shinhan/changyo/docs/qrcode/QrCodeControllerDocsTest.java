package com.shinhan.changyo.docs.qrcode;

import com.shinhan.changyo.api.controller.qrcode.QrCodeController;
import com.shinhan.changyo.api.controller.qrcode.request.EditAmountRequest;
import com.shinhan.changyo.api.controller.qrcode.request.EditTitleRequest;
import com.shinhan.changyo.api.controller.qrcode.request.QrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.request.SimpleQrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeDetailResponse;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeResponses;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
import com.shinhan.changyo.api.service.qrcode.QrCodeQueryService;
import com.shinhan.changyo.api.service.qrcode.QrCodeService;
import com.shinhan.changyo.api.service.qrcode.dto.*;
import com.shinhan.changyo.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QrCodeControllerDocsTest.class)
public class QrCodeControllerDocsTest extends RestDocsSupport {
    private final QrCodeService qrCodeService = mock(QrCodeService.class);
    private final QrCodeQueryService qrCodeQueryService = mock(QrCodeQueryService.class);

    @Override
    protected Object initController() {
        return new QrCodeController(qrCodeQueryService, qrCodeService);
    }

    @DisplayName("보증금 QR코드 생성 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void createQr() throws Exception {
        QrCodeRequest request = QrCodeRequest.builder()
                .accountId(1L)
                .amount(20000)
                .title("프라이빗 객실")
                .build();

        QrCodeDetailResponse response = QrCodeDetailResponse.builder()
                .qrCodeId(1L)
                .bankCode("088")
                .accountNumber("11345678915")
                .title("프라이빗 객실")
                .customerName("홍진식")
                .amount(20000)
                .base64QrCode("123123123")
                .url("www.naver.com")
                .build();

        given(qrCodeService.createQrcode(any(QrCodeDto.class)))
                .willReturn(response);

        mockMvc.perform(
                        post("/qrcode")
                                .header("Authentication", "test")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-qrCode",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("accountId").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별 키"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("data.customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("data.base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("data.url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }

    @DisplayName("단순송금 QR코드 생성 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void createSimpleQr() throws Exception {
        SimpleQrCodeRequest request = SimpleQrCodeRequest.builder()
                .accountNumber("111123123456")
                .bankCode("088")
                .amount(20000)
                .build();

        SimpleQrCodeResponse response = SimpleQrCodeResponse.builder()
                .simpleQrCodeId(1L)
                .url("https://temp/url")
                .base64QrCode("base64base64base64")
                .memberName("홍진식")
                .accountNumber("11345678915")
                .bankCode("088")
                .amount(20000)
                .createdDate("2023-09-14 23:42")
                .build();

        given(qrCodeService.createSimpleQrcode(any(SimpleQrCodeDto.class), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        post("/qrcode/simple")
                                .header("Authentication", "test")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("create-simpleQrCode",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("입금받을 계좌번호"),
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("입금받을 계좌 은행번호"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액")
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
                                fieldWithPath("data.simpleQrCodeId").type(JsonFieldType.NUMBER)
                                        .description("간편송금 QR 식별키"),
                                fieldWithPath("data.url").type(JsonFieldType.STRING)
                                        .description("간편송금 URL"),
                                fieldWithPath("data.base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("data.memberName").type(JsonFieldType.STRING)
                                        .description("회원이름"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌번호"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행코드"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.STRING)
                                        .description("생성일")
                        )
                ));
    }


    @DisplayName("보증금 QR코드 금액 변경 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void editAmount() throws Exception {
        EditAmountRequest request = EditAmountRequest.builder()
                .amount(15000)
                .build();

        QrCodeDetailResponse response = QrCodeDetailResponse.builder()
                .qrCodeId(1L)
                .bankCode("088")
                .accountNumber("11345678915")
                .title("프라이빗 객실")
                .customerName("홍진식")
                .amount(15000)
                .base64QrCode("123123123")
                .url("www.naver.com")
                .build();

        given(qrCodeService.editAmount(any(EditAmountDto.class)))
                .willReturn(response);

        mockMvc.perform(
                        patch("/qrcode/amount/1")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("edit-amount-qrcode",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("변경 금액")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("data.customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("data.base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("data.url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }


    @DisplayName("보증금 QR코드 제목 변경 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void editTitle() throws Exception {
        EditTitleRequest request = EditTitleRequest.builder()
                .title("제목 변경 하겠습니다")
                .build();

        QrCodeDetailResponse response = QrCodeDetailResponse.builder()
                .qrCodeId(1L)
                .bankCode("088")
                .accountNumber("11345678915")
                .title("제목 변경 하겠습니다")
                .customerName("홍진식")
                .amount(20000)
                .base64QrCode("123123123")
                .url("www.naver.com")
                .build();

        given(qrCodeService.editTitle(any(EditTitleDto.class)))
                .willReturn(response);

        mockMvc.perform(
                        patch("/qrcode/title/1")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("edit-title-qrcode",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("변경 제목")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("data.customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("data.base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("data.url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }


    @DisplayName("보증금 QR 삭제 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void removeQr() throws Exception {
        Boolean result = true;

        given(qrCodeService.removeQrCode(anyLong(), anyString()))
                .willReturn(result);

        mockMvc.perform(
                        delete("/qrcode/remove/1")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("remove-qrcode",
                        preprocessRequest(prettyPrint()),
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


    @DisplayName("보증금 QR코드 상세조회 API")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getQr() throws Exception {

        QrCodeDetailResponse response = QrCodeDetailResponse.builder()
                .qrCodeId(1L)
                .bankCode("088")
                .accountNumber("11345678915")
                .title("프라이빗 객실")
                .customerName("홍진식")
                .amount(20000)
                .base64QrCode("123123123")
                .url("www.naver.com")
                .build();

        given(qrCodeService.getQrCode(anyLong(), anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/qrcode/1")
                                .header("Authentication", "test")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-qrCode",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("data.bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("data.customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("data.amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("data.base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("data.url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }


    @DisplayName("보증금 QR코드 목록 조회")
    @Test
    @WithMockUser(roles = "MEMBER")
    void getQrs() throws Exception {

        QrCodeResponse response1 = QrCodeResponse.builder()
                .qrCodeId(1L)
                .accountNumber("11345678915")
                .title("프라이빗 객실")
                .amount(20000)
                .bankCode("088")
                .build();

        QrCodeResponse response2 = QrCodeResponse.builder()
                .qrCodeId(2L)
                .accountNumber("321561235")
                .title("일반 객실")
                .amount(10000)
                .bankCode("088")
                .build();

        List<QrCodeResponse> qrCodeLst = List.of(response1, response2);
        QrCodeResponses response = QrCodeResponses.of(qrCodeLst);

        given(qrCodeQueryService.getQrCodes(anyString()))
                .willReturn(response);

        mockMvc.perform(
                        get("/qrcode")
                                .header("Authentication", "test")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-qrCodes",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data.qrCodeSize").type(JsonFieldType.NUMBER)
                                        .description("QR코드 개수"),
                                fieldWithPath("data.qrCodeResponses[].qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("data.qrCodeResponses[].accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("data.qrCodeResponses[].title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("data.qrCodeResponses[].amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("data.qrCodeResponses[].bankCode").type(JsonFieldType.STRING)
                                        .description("은행코드")
                        )
                ));
    }


}
