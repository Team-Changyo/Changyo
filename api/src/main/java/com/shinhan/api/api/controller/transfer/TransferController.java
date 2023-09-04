package com.shinhan.api.api.controller.transfer;

import com.shinhan.api.api.controller.ApiResponse;
import com.shinhan.api.api.controller.transfer.request.OneTransferRequest;
import com.shinhan.api.api.controller.transfer.request.TransferRequest;
import com.shinhan.api.api.controller.transfer.response.OneTransferResponse;
import com.shinhan.api.api.controller.transfer.response.TransferResponse;
import com.shinhan.api.api.service.transfer.TransferQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TransferController {

    private final TransferQueryService transferQueryService;

    @PostMapping("/v1/transfer/krw")
    public ApiResponse<TransferResponse> transfer(@RequestBody TransferRequest request) {
        return ApiResponse.ok(null);
    }

    @PostMapping("/v1/auth/1transfer")
    public ApiResponse<OneTransferResponse> oneTransfer(@RequestBody OneTransferRequest request) {
        return ApiResponse.ok(null);
    }
}
