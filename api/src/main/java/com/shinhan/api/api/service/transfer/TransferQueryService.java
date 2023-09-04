package com.shinhan.api.api.service.transfer;

import com.shinhan.api.domain.trade.repository.TradeQueryRepository;
import com.shinhan.api.domain.transfer.repository.TransferQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferQueryService {

    private final TransferQueryRepository transferQueryRepository;

    public void transfer() {
        // TODO: 2023-09-04 거래로직 구현
    }
}

