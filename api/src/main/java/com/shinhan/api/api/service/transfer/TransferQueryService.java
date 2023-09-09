package com.shinhan.api.api.service.transfer;

import com.shinhan.api.api.controller.transfer.response.OneTransferResponse;
import com.shinhan.api.api.controller.transfer.response.TransferResponse;
import com.shinhan.api.api.service.transfer.dto.OneTransferDto;
import com.shinhan.api.api.service.transfer.dto.TransferDto;
import com.shinhan.api.domain.account.Account;
import com.shinhan.api.domain.account.repository.AccountRepository;
import com.shinhan.api.domain.trade.Trade;
import com.shinhan.api.domain.trade.repository.TradeRepository;
import com.shinhan.api.domain.transfer.repository.TransferQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class TransferQueryService {

    private final AccountRepository accountRepository;
    private final TradeRepository tradeRepository;

    public TransferResponse transfer(TransferDto dto, LocalDateTime tradeDateTime) {
        //출금
        Account withdrawalAccount = accountRepository.findByAccountNumber(dto.getWithdrawalAccountNumber())
            .orElseThrow(NoSuchElementException::new);
        int withdrawalResult = withdrawalAccount.withdrawal(dto.getAmount());

        Trade withdrawalTrade = Trade.builder()
            .tradeDateTime(tradeDateTime)
            .summary("출금")
            .withdrawalAmount(dto.getAmount())
            .depositAmount(0)
            .content(dto.getWithdrawalMemo())
            .balance(withdrawalResult)
            .status(1)
            .dealershipName("신한")
            .account(withdrawalAccount)
            .build();
        tradeRepository.save(withdrawalTrade);

        //입금
        Account depositAccount = accountRepository.findByAccountNumber(dto.getDepositAccountNumber())
            .orElseThrow(NoSuchElementException::new);
        int depositResult = depositAccount.deposit(dto.getAmount());

        Trade depositTrade = Trade.builder()
            .tradeDateTime(tradeDateTime)
            .summary("입금")
            .withdrawalAmount(0)
            .depositAmount(dto.getAmount())
            .content(dto.getDepositMemo())
            .balance(depositResult)
            .status(1)
            .dealershipName("신한")
            .account(depositAccount)
            .build();
        tradeRepository.save(depositTrade);

        return TransferResponse.builder()
            .withdrawalAccountNumber(dto.getWithdrawalAccountNumber())
            .depositBankCode(dto.getDepositBankCode())
            .depositAccountNumber(dto.getDepositAccountNumber())
            .amount(dto.getAmount())
            .depositMemo(dto.getDepositMemo())
            .withdrawalMemo(dto.getWithdrawalMemo())
            .result(withdrawalResult)
            .build();
    }

    public OneTransferResponse oneTransfer(OneTransferDto dto, LocalDateTime tradeDateTime) {
        Account depositAccount = accountRepository.findByAccountNumber(dto.getAccountNumber())
            .orElseThrow(NoSuchElementException::new);
        int depositResult = depositAccount.deposit(1);

        Trade depositTrade = Trade.builder()
            .tradeDateTime(tradeDateTime)
            .summary("입금")
            .withdrawalAmount(0)
            .depositAmount(1)
            .content(dto.getMemo())
            .balance(depositResult)
            .status(1)
            .dealershipName("신한")
            .account(depositAccount)
            .build();
        tradeRepository.save(depositTrade);

        return OneTransferResponse.of(depositAccount);
    }
}

