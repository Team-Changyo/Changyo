package com.shinhan.api.api.service.transfer;

import com.shinhan.api.api.controller.transfer.response.OneTransferResponse;
import com.shinhan.api.api.controller.transfer.response.TransferResponse;
import com.shinhan.api.api.service.transfer.dto.OneTransferDto;
import com.shinhan.api.api.service.transfer.dto.TransferDto;
import com.shinhan.api.domain.account.Account;
import com.shinhan.api.domain.account.repository.AccountRepository;
import com.shinhan.api.domain.trade.repository.TradeQueryRepository;
import com.shinhan.api.domain.transfer.repository.TransferQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class TransferQueryService {

    private final AccountRepository accountRepository;
    private final TransferQueryRepository transferQueryRepository;

    public TransferResponse transfer(TransferDto dto) {
        //출금
        Account withdrawalAccount = accountRepository.findByAccountNumber(dto.getWithdrawalAccountNumber())
            .orElseThrow(NoSuchElementException::new);
        int result = withdrawalAccount.withdrawal(dto.getAmount());

        //입금
        Account depositAccount = accountRepository.findByAccountNumber(dto.getDepositAccountNumber())
            .orElseThrow(NoSuchElementException::new);
        depositAccount.deposit(dto.getAmount());

        return TransferResponse.builder()
            .withdrawalAccountNumber(dto.getWithdrawalAccountNumber())
            .depositBankCode(dto.getDepositBankCode())
            .depositAccountNumber(dto.getDepositAccountNumber())
            .amount(dto.getAmount())
            .depositMemo(dto.getDepositMemo())
            .withdrawalMemo(dto.getWithdrawalMemo())
            .result(result)
            .build();
    }

    public OneTransferResponse oneTransfer(OneTransferDto dto) {
        Account depositAccount = accountRepository.findByAccountNumber(dto.getAccountNumber())
            .orElseThrow(NoSuchElementException::new);
        depositAccount.deposit(1);

        return OneTransferResponse.of(depositAccount);
    }
}

