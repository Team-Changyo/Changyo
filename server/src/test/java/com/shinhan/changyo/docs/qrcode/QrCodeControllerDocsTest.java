package com.shinhan.changyo.docs.qrcode;

import com.shinhan.changyo.api.controller.qrcode.QrCodeController;
import com.shinhan.changyo.api.controller.qrcode.request.EditAmountRequest;
import com.shinhan.changyo.api.controller.qrcode.request.EditTitleRequest;
import com.shinhan.changyo.api.controller.qrcode.request.QrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.request.SimpleQrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeDetailResponse;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
import com.shinhan.changyo.api.service.qrcode.QrCodeQueryService;
import com.shinhan.changyo.api.service.qrcode.QrCodeService;
import com.shinhan.changyo.api.service.qrcode.dto.EditAmountDto;
import com.shinhan.changyo.api.service.qrcode.dto.EditTitleDto;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeDto;
import com.shinhan.changyo.api.service.qrcode.dto.SimpleQrCodeDto;
import com.shinhan.changyo.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

public class QrCodeControllerDocsTest extends RestDocsSupport {
    private final QrCodeService qrCodeService = mock(QrCodeService.class);
    private final QrCodeQueryService qrCodeQueryService = mock(QrCodeQueryService.class);
    @Override
    protected Object initController() {
        return new QrCodeController(qrCodeQueryService, qrCodeService);
    }

    @DisplayName("보증금 QR코드 생성 API")
    @Test
    void createQr() throws Exception{
        QrCodeRequest request = QrCodeRequest.builder()
                .url("www.naver.com")
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
                post("/api/qrcode-management/qrcode")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-qrCode",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL"),
                                fieldWithPath("accountId").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별 키"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목")
                        ),
                        responseFields(
                                fieldWithPath("qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                    ));
    }

    @DisplayName("단순송금 QR코드 생성 API")
    @Test
    void createSimpleQr() throws Exception{
        SimpleQrCodeRequest request = SimpleQrCodeRequest.builder()
                .url("www.naver.com")
                .accountId(1L)
                .amount(20000)
                .build();

        SimpleQrCodeResponse response = SimpleQrCodeResponse.builder()
                .bankCode("088")
                .accountNumber("11345678915")
                .customerName("홍진식")
                .amount(20000)
                .base64QrCode("123123123")
                .url("www.naver.com")
                .build();

        given(qrCodeService.createSimpleQrcode(any(SimpleQrCodeDto.class)))
                .willReturn(response);

        mockMvc.perform(
                        post("/api/qrcode-management/qrcode/simple")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("create-simpleQrCode",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL"),
                                fieldWithPath("accountId").type(JsonFieldType.NUMBER)
                                        .description("계좌 식별 키"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액")
                        ),
                        responseFields(
                                fieldWithPath("qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }

    @DisplayName("보증금 QR코드 금액 변경 API")
    @Test
    void editAmount() throws Exception{
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
                        patch("/api/qrcode-management/qrcode/amount/1")
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
                                fieldWithPath("qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }


    @DisplayName("보증금 QR코드 제목 변경 API")
    @Test
    void editTitle() throws Exception{
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
                        patch("/api/qrcode-management/qrcode/title/1")
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
                                fieldWithPath("qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }


    @DisplayName("보증금 QR 삭제 API")
    @Test
    void removeQr() throws Exception{
        Boolean result = true;

        given(qrCodeService.removeQrCode(anyLong()))
                .willReturn(result);

        mockMvc.perform(
                        delete("/api/qrcode-management/qrcode/remove/1")
                )
                .andDo(print())
                .andExpect(status().isFound())
                .andDo(document("remove-qrcode",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.BOOLEAN)
                                        .description("삭제 결과: true 또는 false")
                        )
                ));
    }


    @DisplayName("보증금 QR코드 상세조회 API")
    @Test
    void getQr() throws Exception{

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

        given(qrCodeService.getQrCode(anyLong()))
                .willReturn(response);

        mockMvc.perform(
                        get("/api/qrcode-management/qrcode/1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-qrCode",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("qrCodeId").type(JsonFieldType.NUMBER)
                                        .description("QR코드 식별 키"),
                                fieldWithPath("bankCode").type(JsonFieldType.STRING)
                                        .description("은행 코드"),
                                fieldWithPath("accountNumber").type(JsonFieldType.STRING)
                                        .description("계좌 번호"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("QR코드 제목"),
                                fieldWithPath("customerName").type(JsonFieldType.STRING)
                                        .description("계좌번호 실명"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER)
                                        .description("금액"),
                                fieldWithPath("base64QrCode").type(JsonFieldType.STRING)
                                        .description("QR코드 base64"),
                                fieldWithPath("url").type(JsonFieldType.STRING)
                                        .description("결제 페이지 URL")
                        )
                ));
    }


}
