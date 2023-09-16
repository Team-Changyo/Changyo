package com.shinhan.changyo.api.service.auth;

import com.shinhan.changyo.domain.member.repository.MemberQueryRepository;
import com.shinhan.changyo.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhoneAuthenticationService {

    @Value("${sns.apiKey}")
    private String API_KEY;

    @Value("${sns.apiSecretKey}")
    private String API_SECRET_KEY;

    @Value("${sns.from}")
    private String FROM;

    private final MemberQueryRepository memberQueryRepository;
    private final RedisRepository redisRepository;

    public void generateAuthenticationNumber(String phoneNumber) {
        String authenticationNumber = GenerateRandomNumber.generateRandomNumber(6);

        sendMessage(phoneNumber, authenticationNumber);

        Boolean expired = redisRepository.save(phoneNumber, authenticationNumber, 3);
    }

    public void checkAuthenticationNumber(String phoneNumber, String authenticationNumber) {
        String number = redisRepository.findByKey(phoneNumber);
        validateExpiredTime(number);

        validateEqualNumber(authenticationNumber, number);

        redisRepository.expire(phoneNumber);

        validateDuplicatePhoneNumber(phoneNumber);
    }

    private void sendMessage(String phoneNumber, String authenticationNumber) {
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, "https://api.coolsms.co.kr");
        Message message = new Message();
        message.setFrom(FROM);
        message.setTo(phoneNumber);
        message.setText(String.format("[인증번호: %s] 챙겨요계정 인증번호입니다.", authenticationNumber));

        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void validateExpiredTime(String number) {
        if (number == null) {
            throw new IllegalArgumentException("유효시간이 만료되었습니다.");
        }
    }

    private static void validateEqualNumber(String authenticationNumber, String number) {
        if (!number.equals(authenticationNumber)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }
    }

    private void validateDuplicatePhoneNumber(String phoneNumber) {
        boolean result = memberQueryRepository.existsPhoneNumber(phoneNumber);
        if (result) {
            throw new IllegalArgumentException("이미 사용중인 연락처입니다.");
        }
    }
}