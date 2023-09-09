package com.shinhan.changyo.domain.qrcode;

import com.shinhan.changyo.domain.TimeBaseEntity;
import com.shinhan.changyo.domain.account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * QR코드 Entity
 *
 * @author 홍진식
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QrCode extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_code_id")
    private Long qrCodeId;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false, length = 30)
    private String title;

    // TODO: 2023-09-08 홍진식 : 변수명 체크 필요
    @Column(nullable = false, name = "base64_qr_code")
    private String base64QrCode;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    @Builder
    public QrCode(Long qrCodeId, String url, int amount, String title, String base64QrCode, boolean active, Account account) {
        this.qrCodeId = qrCodeId;
        this.url = url;
        this.amount = amount;
        this.title = title;
        this.base64QrCode = base64QrCode;
        this.active = active;
        this.account = account;
    }

    // 비즈니스 로직

    public void editAmount(int amount){
        this.amount = amount;
    }

    public void editTitle(String title){
        this.title = title;
    }

    public void remove() {
        this.active = false;
    }
}
