package com.shinhan.changyo.api.service.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.shinhan.changyo.api.controller.qrcode.request.QrCodeRequest;
import com.shinhan.changyo.api.controller.qrcode.response.QrCodeResponse;
import com.shinhan.changyo.api.controller.qrcode.response.SimpleQrCodeResponse;
import com.shinhan.changyo.api.service.qrcode.dto.QrCodeDto;
import com.shinhan.changyo.api.service.qrcode.dto.SimpleQrCodeDto;
import com.shinhan.changyo.domain.account.Account;
import com.shinhan.changyo.domain.account.repository.AccountRepository;
import com.shinhan.changyo.domain.qrcode.QrCode;
import com.shinhan.changyo.domain.qrcode.repository.QrCodeRepository;
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
    /**
     * QR코드 증록
     *
     * @param dto 등록할 qr코드 정보
     * @return qr코드 정보
     */

    public Long createQrcode(QrCodeDto dto) {
        try{
            // QR코드 생성
            String qrCodeBase64 = createQR(dto.getUrl());

            // entity 생성
            Account findAccount = accountRepository.findById(dto.getAccountId()).orElseThrow(() -> new IllegalArgumentException("계좌 정보가 존재하지 않습니다."));
            QrCode qrCode = dto.toEntity(qrCodeBase64, findAccount);

            // qr코드 등록
            qrCodeRepository.save(qrCode);

            return qrCode.getQrCodeId();
        } catch (Exception e) {
            log.debug(e.toString());
            throw new RuntimeException(e);
        }
    }

    public SimpleQrCodeResponse createSimpleQrcode(SimpleQrCodeDto dto) {
        try{
            // QR코드 생성
            String qrCodeBase64 = createQR(dto.getUrl());

            // entity 생성
            Account findAccount = accountRepository.findById(dto.getAccountId()).orElseThrow(() -> new IllegalArgumentException("계좌 정보가 존재하지 않습니다."));

            SimpleQrCodeResponse response = SimpleQrCodeResponse.builder()
                    .bankCode(findAccount.getBankCode())
                    .accountNumber(findAccount.getAccountNumber())
                    .customerName(findAccount.getCustomerName())
                    .amount(dto.getAmount())
                    .storeFileName(qrCodeBase64)
                    .url(dto.getUrl())
                    .build();

            return response;
        } catch (Exception e) {
            log.debug(e.toString());
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param url : 계좌 url
     * @return QR코드 base64
     * @throws Exception
     */
    public String createQR(String url) throws Exception {

        BitMatrix bitMatrix=null;
        MatrixToImageConfig matrixToImageConfig=null;
        // QRCode에 담고 싶은 정보를 문자열로 표시한다. url이든 뭐든 가능하다.
        String codeInformation = url;

        // 큐알코드 바코드 및 배경 색상값
        int onColor =   0xFF2e4e96; // 바코드 색
        int offColor = 0xFFFFFFFF; // 배경 색

        // 이름 그대로 QRCode 만들때 쓰는 클래스다
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // 큐알 전경과 배경의 색을 정한다. 값을 넣지 않으면 검정코드에 흰 배경이 기본값이다.
        matrixToImageConfig = new MatrixToImageConfig(onColor,offColor);
        Map<EncodeHintType,String> hints =new HashMap<>();
        // QRCode 생성시 조건을 넣어서 만들 수 있게 한다.
        // 여기서 Error_Correction의 경우 큐알 코드가 좀더 자글자글하게 만들어 주는 대신 큐알이 가려져도 인식할 가능성이 더욱 높아진다.

       /*
 	https://zxing.github.io/zxing/apidocs/com/google/zxing/qrcode/decoder/ErrorCorrectionLevel.html
        Enum Constants
        L = ~7% correction
        M = ~15% correction
        Q = ~25% correction
        H = ~30% correction
        */
        hints.put(EncodeHintType.ERROR_CORRECTION,"L");


        // QRCode 전체 크기
        // 단위는 fixel
        int width=1000;
        int height=1000;

        // 내부에 빈 공간만들 빈 공간 -> oncolor로 만들어진다.
        //int regionWidth=100;
        //int regionHeight=100;

        try {
            // bitMatrix 형식으로 QRCode를 만든다.
            bitMatrix = qrCodeWriter.encode(codeInformation, BarcodeFormat.QR_CODE,width, height);
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
        int width=bitMatrix.getWidth();
        int height=bitMatrix.getHeight();

        // 빈 공간의 위치(중앙으로 설정했다.)
        int left=(width-regionWidth)/2;
        int top=(height-regionHeight)/2;

        // 빈 공간 생성하기(이때 색은 offColor)
        bitMatrix.setRegion(left,top,regionWidth,regionHeight);
        // 빈 공간의 색을 배경색으로 반전시킨다.(fixel 단위로 찾아서 색을 뒤집는다.)
        for (int y = top; y <= top+regionHeight; y++) {
            for (int x = left; x <= left+regionWidth; x++) {
                if(bitMatrix.get(x, y)){
                    bitMatrix.unset(x,y);
                };
            }
        }
        return bitMatrix;
    }

}
