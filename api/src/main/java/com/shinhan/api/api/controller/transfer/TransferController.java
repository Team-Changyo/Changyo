package com.shinhan.api.api.controller.transfer;

import com.shinhan.api.api.controller.ApiResponse;
import com.shinhan.api.api.controller.transfer.request.OneTransferRequest;
import com.shinhan.api.api.controller.transfer.request.TransferRequest;
import com.shinhan.api.api.controller.transfer.response.OneTransferResponse;
import com.shinhan.api.api.controller.transfer.response.TransferResponse;
import com.shinhan.api.api.service.transfer.TransferService;
import com.shinhan.api.api.service.transfer.dto.OneTransferDto;
import com.shinhan.api.api.service.transfer.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/v1/transfer/krw")
    public ApiResponse<TransferResponse> transfer(@Valid @RequestBody TransferRequest request) {
        log.debug("call TransferController#transfer");
        log.debug("TransferRequest={}", request);

        TransferDto dto = request.toTransferDto();
        TransferResponse response = transferService.transfer(dto, LocalDateTime.now());
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }

    @PostMapping("/v1/auth/1transfer")
    public ApiResponse<OneTransferResponse> oneTransfer(@Valid @RequestBody OneTransferRequest request) {
        log.debug("call TransferController#oneTransfer");
        log.debug("OneTransferRequest={}", request);

        OneTransferDto dto = request.toOneTransferDto();
        OneTransferResponse response = transferService.oneTransfer(dto, LocalDateTime.now());
        log.debug("response={}", response);

        return ApiResponse.ok(response);
    }
}
