package com.shinhan.changyo.domain.account;

import com.shinhan.changyo.domain.TimeBaseEntity;
import com.shinhan.changyo.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 게좌 Entity
 *
 * @author 최영환
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    @Column(nullable = false, length = 5)
    private String bankCode;
    @Column(unique = true, nullable = false, length = 14)
    private String accountNumber;
    @Column(nullable = false)
    private int balance;
    @Column(nullable = false, length = 20)
    private String productName;
    @Column(nullable = false, length = 20)
    private String customerName;
    @Column(unique = true, length = 50)
    private String title;
    @Column(nullable = false)
    private Boolean mainAccount;
    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Account(String bankCode, String accountNumber, int balance, String productName, String customerName, String title, Boolean mainAccount, Boolean active, Member member) {
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.productName = productName;
        this.customerName = customerName;
        this.title = title;
        this.mainAccount = mainAccount;
        this.active = active;
        this.member = member;
    }

    // == Business Logics ==//

    /**
     * 주계좌 설정
     */
    public void setMainAccount() {
        this.mainAccount = true;
    }

    /**
     * 주계좌 설정 해제
     */
    public void setSubAccount() {
        this.mainAccount = false;
    }

    public void editBalance(int balance) {
        this.balance = balance;
    }

    public void editTitle(String title) {
        this.title = title;
    }

    public void editMainAccount() {
        if(this.mainAccount){
            this.mainAccount = false;
        }else{
            this.mainAccount = true;
        }
    }

    public void remove() {
        this.active = false;
    }
}
