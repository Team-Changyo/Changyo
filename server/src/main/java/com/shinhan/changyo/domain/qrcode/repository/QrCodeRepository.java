package com.shinhan.changyo.domain.qrcode.repository;

import com.shinhan.changyo.domain.qrcode.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * QR 코드 레포지토리
 *
 * @author 홍진식
 */

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
}
