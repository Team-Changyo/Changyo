package com.shinhan.api.api.service.transfer;

import com.shinhan.api.api.controller.transfer.response.OneTransferResponse;
import com.shinhan.api.api.controller.transfer.response.TransferResponse;
import com.shinhan.api.api.service.transfer.dto.OneTransferDto;
import com.shinhan.api.api.service.transfer.dto.TransferDto;
import com.shinhan.api.domain.trade.repository.TradeQueryRepository;
import com.shinhan.api.domain.transfer.repository.TransferQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferQueryService {

    private final TransferQueryRepository transferQueryRepository;

    public TransferResponse transfer(TransferDto dto) {
        // TODO: 2023-09-04 거래로직 구현
        return null;
    }

    public OneTransferResponse oneTransfer(OneTransferDto dto) {
        // TODO: 2023-09-05 거래 로직 구현
        return null;
    }
}

