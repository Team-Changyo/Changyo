package com.shinhan.changyo.api.service.auth;

import com.shinhan.changyo.domain.member.repository.MemberQueryRepository;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class PhoneAuthenticationService {

    @Value("${sns.apiKey}")
    private String API_KEY;

    @Value("${sns.apiSecretKey}")
    private String API_SECRET_KEY;

    @Value("${sns.from}")
    private String FROM;

    private final MemberQueryRepository memberQueryRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;

    public PhoneAuthenticationService(MemberQueryRepository memberQueryRepository, RedisTemplate<String, String> redisTemplate) {
        this.memberQueryRepository = memberQueryRepository;
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void generateAuthenticationNumber(String phoneNumber) {
        String authenticationNumber = generateRandomNumber();

        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, "https://api.coolsms.co.kr");
        Message message = new Message();
        message.setFrom(FROM);
        message.setTo(phoneNumber);
        message.setText(String.format("[인증번호: %s] 챙겨요계정 인증번호입니다.", authenticationNumber));

        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException e) {
            System.out.println("test");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        valueOperations.set(phoneNumber, authenticationNumber);

        Boolean expire = redisTemplate.expire(phoneNumber, 3, TimeUnit.MINUTES);
    }

    public void checkAuthenticationNumber(String phoneNumber, String authenticationNumber) {
        String number = valueOperations.get(phoneNumber);
        if (number == null) {
            throw new IllegalArgumentException("유효시간이 만료되었습니다.");
        }

        if (!number.equals(authenticationNumber)) {
            throw new IllegalArgumentException("인증번호가 일치하지 않습니다.");
        }

        redisTemplate.expire(phoneNumber, 0, TimeUnit.SECONDS);

        boolean result = memberQueryRepository.existsPhoneNumber(phoneNumber);
        if (result) {
            throw new IllegalArgumentException("이미 사용중인 연락처입니다.");
        }
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(1000000);
        return String.format("%06d", number);
    }
}