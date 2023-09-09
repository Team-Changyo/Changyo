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
    @Column(nullable = false, name = "store_file_name")
    private String storeFileName;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    @Builder
    public QrCode(Long qrCodeId, String url, int amount, String title, String storeFileName, boolean active, Account account) {
        this.qrCodeId = qrCodeId;
        this.url = url;
        this.amount = amount;
        this.title = title;
        this.storeFileName = storeFileName;
        this.active = active;
        this.account = account;
    }
}
