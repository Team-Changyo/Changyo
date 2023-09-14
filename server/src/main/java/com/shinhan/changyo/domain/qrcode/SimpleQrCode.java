package com.shinhan.changyo.domain.qrcode;

import com.shinhan.changyo.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 간편 송금용 QR 코드 엔티티
 *
 * @author 최영환
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SimpleQrCode extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simple_qr_code_id")
    private Long id;
    @Column(nullable = false, length = 20)
    private String memberName;
    @Column(nullable = false, length = 14)
    private String accountNumber;
    @Column(nullable = false, length = 5)
    private String bankCode;
    private int amount;
    @Column(columnDefinition = "text")
    private String base64QrCode;
    @Column
    private String url;

    @Builder
    private SimpleQrCode(Long id, String memberName, String accountNumber, String bankCode, int amount, String base64QrCode, String url) {
        this.memberName = memberName;
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.amount = amount;
        this.base64QrCode = base64QrCode;
        this.url = url;
    }

    // == Business Logic == //
    public void editUrlAndQrCodeBase64(String url, String base64QrCode) {
        this.url = url;
        this.base64QrCode = base64QrCode;
    }
}
