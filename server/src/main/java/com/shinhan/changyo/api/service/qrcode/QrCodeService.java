package com.shinhan.changyo.api.service.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeDetailResponse;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
import com.shinhan.changyo.api.service.qrcode.dto.EditAmountDto;
import com.shinhan.changyo.api.service.qrcode.dto.EditTitleDto;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeDto;
import com.shinhan.changyo.api.service.qrcode.dto.SimpleQrCodeDto;
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

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


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

    /**
     * QR코드 증록
     *
     * @param dto 등록할 qr코드 정보
     * @return qr코드 정보
     */

    public QrCodeDetailResponse createQrcode(QrCodeDto dto) {
        try {
            StringBuilder url = new StringBuilder("https://j9c205.ssafy.io/remittance/deposit?qrCodeId=");
            String qrCodeBase64 = createQR(url.toString());

            Account findAccount = accountRepository.findById(dto.getAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("계좌 정보가 존재하지 않습니다."));

            if (!findAccount.getMember().getLoginId().equals(dto.getLoginId())) {
                throw new IllegalAccessException("잘못된 접근입니다.");
            }

            QrCode saveQrCode = qrCodeRepository.save(dto.toEntity(url.toString(), qrCodeBase64, findAccount));
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
        String codeInformation = url;
        int onColor = 0xFF2e4e96; // 바코드 색
        int offColor = 0xFFFFFFFF; // 배경 색

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(onColor, offColor);
        Map<EncodeHintType, String> hints = new HashMap<>();

       /*
 	https://zxing.github.io/zxing/apidocs/com/google/zxing/qrcode/decoder/ErrorCorrectionLevel.html
        Enum Constants
        L = ~7% correction
        M = ~15% correction
        Q = ~25% correction
        H = ~30% correction
        */
        hints.put(EncodeHintType.ERROR_CORRECTION, "L");


        // QRCode 전체 크기
        // 단위는 fixel
        int width = 200;
        int height = 200;

        // 내부에 빈 공간만들 빈 공간 -> oncolor로 만들어진다.
        //int regionWidth=100;
        //int regionHeight=100;

        try {
            // bitMatrix 형식으로 QRCode를 만든다.
            bitMatrix = qrCodeWriter.encode(codeInformation, BarcodeFormat.QR_CODE, width, height);
            // QRCode 중간에 빈공간을 만들고 색을 offColor로 바꿔주는 메소드
//             bitMatrix= emptyQR(bitMatrix,height,width); // QR내부에 빈 공간 만드는 메소드(사용할 경우 hint의 error_correction 을 반드시 높여줘야 합니다)
        } catch (Exception e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, matrixToImageConfig);

        // 이제 만들어본 QRCode를 저장해보자
//        String savePath = "c:\test\";
//        String saveFileName = "qrImage.png";
//        File file = new File(savePath);
//        if(!file.exists()) {
//            file.mkdirs();
//            // 리눅스 서버에 저장하는 경우 파일 접근 권한을 줘야 한다.
//        }
        // 파일은 저장하고 싶은대로 저장하면 된다.
        // buffer나 stream는 공부를 더 해봐야 하는 부분이다.

//        // bufferedImage 를 이용한 파일 저장 -> 방식 1
//        BufferedImage bufferedImage=null;
//        bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
//        File saveFile=new File(savePath+qrName);
//        ImageIO.write(bufferedImage, "png", saveFile);
//
//        // fileOutputStream 을 이용한 파일 저장 -> 방식 2
//        FileOutputStream fileOutputStream=new FileOutputStream(new File(savePath+qrName));
//        fileOutputStream.write(outputStream.toByteArray());
//        fileOutputStream.close();

        // byteArray를 base64로 변환한 이유는 프론트에서 파일경로가 아닌 binary 형식으로 전송해서 보여주기 위해서다.
        // 이렇게 할 경우 DB에 이미지를 저장하지 않고 화면에 보여줄 수 있다.
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    private BitMatrix emptyQR(BitMatrix bitMatrix, int regionHeight, int regionWidth) {
        // 이 메소드는 bitmatrix에 네모난 공간을 만드는 것이다.

        // 빈 공간의 넓이와 높이
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();

        // 빈 공간의 위치(중앙으로 설정했다.)
        int left = (width - regionWidth) / 2;
        int top = (height - regionHeight) / 2;

        // 빈 공간 생성하기(이때 색은 offColor)
        bitMatrix.setRegion(left, top, regionWidth, regionHeight);
        // 빈 공간의 색을 배경색으로 반전시킨다.(fixel 단위로 찾아서 색을 뒤집는다.)
        for (int y = top; y <= top + regionHeight; y++) {
            for (int x = left; x <= left + regionWidth; x++) {
                if (bitMatrix.get(x, y)) {
                    bitMatrix.unset(x, y);
                }
            }
        }
        return bitMatrix;
    }

    public QrCodeDetailResponse editAmount(EditAmountDto dto) {
        QrCode findQrCode = qrCodeRepository.findById(dto.getQrCodeId()).orElseThrow(() -> new IllegalArgumentException("QR코드 정보가 존재하지 않습니다."));
        findQrCode.editAmount(dto.getAmount());
        return QrCodeDetailResponse.of(findQrCode);
    }

    public QrCodeDetailResponse editTitle(EditTitleDto dto) {
        QrCode findQrCode = qrCodeRepository.findById(dto.getQrCodeId()).orElseThrow(() -> new IllegalArgumentException("QR코드 정보가 존재하지 않습니다."));
        findQrCode.editTitle(dto.getTitle());
        return QrCodeDetailResponse.of(findQrCode);
    }

    public Boolean removeQrCode(Long qrCodeId) {
        QrCode findQrCode = qrCodeRepository.findById(qrCodeId).orElseThrow(() -> new IllegalArgumentException("QR코드 정보가 존재하지 않습니다."));
        findQrCode.remove();
        return true;
    }

    public QrCodeDetailResponse getQrCode(Long qrCodeId) {
        QrCode findQrCode = qrCodeRepository.findById(qrCodeId).orElseThrow(() -> new IllegalArgumentException("QR코드 정보가 존재하지 않습니다."));
        return QrCodeDetailResponse.of(findQrCode);
    }
}
