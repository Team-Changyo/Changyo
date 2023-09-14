package com.shinhan.changyo.domain.qrcode.repository;

import com.shinhan.changyo.domain.qrcode.SimpleQrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleQrCodeRepository extends JpaRepository<SimpleQrCode, Long> {
}
