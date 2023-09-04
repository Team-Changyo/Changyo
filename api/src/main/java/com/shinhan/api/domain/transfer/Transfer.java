package com.shinhan.api.domain.transfer;

import com.shinhan.api.domain.TimeBaseEntity;
import com.shinhan.api.domain.account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transfer extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long id;
    private int amount;
    private String depositMemo;
    private String withdrawalMemo;
    private int result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "withdrawal_account_id")
    private Account withdrawalAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_account_id")
    private Account depositAccount;

    @Builder
    private Transfer(int amount, String depositMemo, String withdrawalMemo, int result, Account withdrawalAccount, Account depositAccount) {
        this.amount = amount;
        this.depositMemo = depositMemo;
        this.withdrawalMemo = withdrawalMemo;
        this.result = result;
        this.withdrawalAccount = withdrawalAccount;
        this.depositAccount = depositAccount;
    }
}
