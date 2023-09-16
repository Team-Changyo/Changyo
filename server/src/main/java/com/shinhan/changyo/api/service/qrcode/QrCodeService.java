package com.shinhan.changyo.api.service.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeDetailResponse;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
import com.shinhan.changyo.api.service.util.exception.NoAccountException;
import com.shinhan.changyo.api.service.qrcode.dto.EditAmountDto;
import com.shinhan.changyo.api.service.qrcode.dto.EditTitleDto;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeDto;
import com.shinhan.changyo.api.service.qrcode.dto.SimpleQrCodeDto;
import com.shinhan.changyo.api.service.util.exception.ForbiddenException;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.member.repository.MemberQueryRepository;
import com.shinhan.changyo.domain.qrcode.QrCode;
import com.shinhan.changyo.domain.qrcode.SimpleQrCode;
import com.shinhan.changyo.domain.qrcode.repository.QrCodeRepository;
import com.shinhan.changyo.domain.qrcode.repository.SimpleQrCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * QR코드 서비스
 *
 * @author 홍진식
 */

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class QrCodeService {
    private final QrCodeRepository qrCodeRepository;
    private final AccountRepository accountRepository;
    private final SimpleQrCodeRepository simpleQrCodeRepository;
    private final MemberQueryRepository memberQueryRepository;

    static final String LOGO_PATH = "src/main/resources/static/images/changyoLogo.png";

    /**
     * QR코드 증록
     *
     * @param dto 등록할 qr코드 정보
     * @return qr코드 정보
     */

    public QrCodeDetailResponse createQrcode(QrCodeDto dto) {
        try {
            StringBuilder url = new StringBuilder("https://j9c205.ssafy.io/remittance/deposit?qrCodeId=");
            String qrCodeBase64 = url.toString();

            Account findAccount = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new NoAccountException("계좌 정보가 존재하지 않습니다."));

            if (!findAccount.getMember().getLoginId().equals(dto.getLoginId())) {
                throw new ForbiddenException("계좌 접근 권한이 없습니다.");
            }

            QrCode saveQrCode = qrCodeRepository.save(dto.toEntity(url.toString(), qrCodeBase64, findAccount));

            // ID를 추가해서 QR코드 생성
            url.append(saveQrCode.getQrCodeId());
            qrCodeBase64 = createQR(url.toString());
            saveQrCode.editUrlAndQrCodeBase64(url.toString(), qrCodeBase64);

            return QrCodeDetailResponse.of(saveQrCode);
        } catch (Exception e) {
            log.debug(e.toString());
            throw new RuntimeException(e);
        }
    }

    public SimpleQrCodeResponse createSimpleQrcode(SimpleQrCodeDto dto, String loginId) {
        try {
            Member member = memberQueryRepository.getMemberByLoginId(loginId);
            SimpleQrCode saveQrCode = simpleQrCodeRepository.save(dto.toEntity(member.getName()));
            String url = String.format("https://j9c205.ssafy.io/remittance/normal?simpleQrCodeId=%s", saveQrCode.getId());
            String qrCodeBase64 = createQR(url);
            saveQrCode.editUrlAndQrCodeBase64(url, qrCodeBase64);

            return SimpleQrCodeResponse.of(saveQrCode);

        } catch (Exception e) {
            log.debug(e.toString());
            throw new RuntimeException(e);
        }
    }


    /**
     * @param url : 계좌 url
     * @return QR코드 base64
     * @throws Exception
     */
    public String createQR(String url) throws Exception {

        BitMatrix bitMatrix = null;
        BufferedImage combined = null;
        String codeInformation = url;
        int onColor = 0xFF000000; // 바코드 색
        int offColor = 0xFFFFFFFF; // 배경 색

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(onColor, offColor);
        Map<EncodeHintType, String> hints = new HashMap<>();

        hints.put(EncodeHintType.ERROR_CORRECTION, "L");

        // 이미지 파일을 BufferedImage로 로드
        BufferedImage logoImage = loadImage(LOGO_PATH);

        // QRCode 전체 크기
        // 단위는 fixel
        int width = 200;
        int height = 200;

        try {
            // bitMatrix 형식으로 QRCode를 만든다.
            bitMatrix = qrCodeWriter.encode(codeInformation, BarcodeFormat.QR_CODE, width, height);

            // Logo 삽입
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
            BufferedImage overly = logoImage;

            int deltaHeight = qrImage.getHeight() - overly.getHeight();
            int deltaWidth = qrImage.getWidth() - overly.getWidth();

            combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();

            g.drawImage(qrImage, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            // QR코드 이미지의 정중앙 위치에 덮음.
            g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

        } catch (Exception e) {
            log.debug(e.toString());
            throw new IllegalArgumentException("QR코드 생성 실패 - 내부 서버 문제");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(combined, "png", outputStream);

        byte[] imageBytes = outputStream.toByteArray();
        String base64QRCode = Base64.getEncoder().encodeToString(imageBytes);
        return base64QRCode;
    }

    public static BufferedImage loadImage(String filePath) {
        try {
            File file = new File(filePath);
            return ImageIO.read(file);
        } catch (IOException e) {
            log.debug("message={}", e.getMessage());
            throw new IllegalArgumentException("QR코드 생성 실페 - 내부 서버 문제");
        }
    }

    public QrCodeDetailResponse editAmount(EditAmountDto dto) {
        QrCode findQrCode = qrCodeRepository.findById(dto.getQrCodeId()).orElseThrow(() -> new NoSuchElementException("QR코드 정보가 존재하지 않습니다."));

        if (!checkIsLoginIdAccount(findQrCode, dto.getLoginId())) {
            throw new ForbiddenException("수정 권한이 없습니다.");
        }

        findQrCode.editAmount(dto.getAmount());
        return QrCodeDetailResponse.of(findQrCode);
    }

    public QrCodeDetailResponse editTitle(EditTitleDto dto) {
        QrCode findQrCode = qrCodeRepository.findById(dto.getQrCodeId()).orElseThrow(() -> new NoSuchElementException("QR코드 정보가 존재하지 않습니다."));

        if (!checkIsLoginIdAccount(findQrCode, dto.getLoginId())) {
            throw new ForbiddenException("수정 권한이 없습니다.");
        }

        findQrCode.editTitle(dto.getTitle());
        return QrCodeDetailResponse.of(findQrCode);
    }

    public Boolean removeQrCode(Long qrCodeId, String loginId) {
        QrCode findQrCode = qrCodeRepository.findById(qrCodeId).orElseThrow(() -> new NoSuchElementException("QR코드 정보가 존재하지 않습니다."));

        if (!checkIsLoginIdAccount(findQrCode, loginId)) {
            throw new ForbiddenException("수정 권한이 없습니다.");
        }

        findQrCode.remove();
        return true;
    }

    public QrCodeDetailResponse getQrCode(Long qrCodeId, String loginId) {
        QrCode findQrCode = qrCodeRepository.findById(qrCodeId).orElseThrow(() -> new NoSuchElementException("QR코드 정보가 존재하지 않습니다."));

        if (!checkIsLoginIdAccount(findQrCode, loginId)) {
            throw new ForbiddenException("접근 권한이 없습니다.");
        }
        return QrCodeDetailResponse.of(findQrCode);
    }

    private boolean checkIsLoginIdAccount(QrCode findQrCode, String loginId) {
        if (findQrCode.getAccount().getMember().getLoginId().equals(loginId)) {
            return true;
        }
        return false;
    }
}
