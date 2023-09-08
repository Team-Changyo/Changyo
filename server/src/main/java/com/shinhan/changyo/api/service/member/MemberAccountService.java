package com.shinhan.changyo.api.service.member;

import com.shinhan.changyo.api.controller.member.response.LoginResponse;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

/**
 * 계정 관련 서비스
 * 
 * @author 최영환
 */
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberAccountService {

    private final MemberQueryRepository memberQueryRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    /**
     * 로그인
     *
     * @param loginId 로그인 할 로그인 아이디
     * @param password 로그인 할 비밀번호
     * @return 로그인한 회원 정보
     */
    public LoginResponse login(String loginId, String password) {
        // TODO: 2023-09-08 로그인 처리 논의 필요
        existCheckByLoginId(loginId);

        Member member = memberQueryRepository.getMemberByLoginId(loginId);

        checkActive(member.getActive());

        checkEqualPassword(password, member.getEncryptedPwd());

        return LoginResponse.of(member);
    }

    /**
     * 로그인 아이디 중복 체크
     *
     * @param loginId 중복 체크할 로그인 아이디
     * @throws NoSuchElementException 존재하지 않는 회원인 경우
     */
    private void existCheckByLoginId(String loginId) {
        boolean checkLoginId = memberQueryRepository.existLoginId(loginId);
        if (!checkLoginId) {
            throw new NoSuchElementException("존재하지 않는 회원입니다.");
        }
    }

    /**
     * 회원탈퇴 여부 확인
     *
     * @param active 회원탈퇴 여부
     * @throws NoSuchElementException 탈퇴한 회원일 경우
     */
    private void checkActive(Boolean active) {
        if (!active) {
            throw new NoSuchElementException("존재하지 않는 회원입니다.");
        }
    }

    /**
     * 비밀번호 일치여부 체크
     *
     * @param password 입력된 비밀번호
     * @param savedPwd DB 에 저장된 비밀번호
     * @throws IllegalArgumentException 비밀번호가 서로 다를 경우
     */
    private void checkEqualPassword(String password, String savedPwd) {
        if (bCryptPasswordEncoder.matches(password, savedPwd)) {
            throw new IllegalArgumentException("비밀번호를 확인해주세요.");
        }
    }
}
