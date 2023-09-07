package com.shinhan.changyo.api.service.member;

import com.shinhan.changyo.api.controller.member.response.JoinMemberResponse;
import com.shinhan.changyo.api.service.member.dto.JoinMemberDto;
import com.shinhan.changyo.api.service.member.exception.DuplicateException;
import com.shinhan.changyo.domain.member.Member;
import com.shinhan.changyo.domain.member.repository.MemberQueryRepository;
import com.shinhan.changyo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 관련 서비스
 *
 * @author 최영환
 */

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 가입
     *
     * @param dto 회원 정보
     * @return 가입 성공 회원의 기본 정보
     */
    public JoinMemberResponse join(JoinMemberDto dto) {
        duplicationCheckByLoginId(dto.getLoginId());
        duplicationCheckByPhoneNumber(dto.getPhoneNumber());

        Member savedMember = createMember(dto);

        return JoinMemberResponse.of(savedMember);
    }

    /**
     * 회원 엔티티 생성 및 DB 저장
     * 
     * @param dto 회원 정보
     * @return 가입된 회원 엔티티
     */
    private Member createMember(JoinMemberDto dto) {
        String encryptedPwd = bCryptPasswordEncoder.encode(dto.getPassword());
        Member member = dto.toEntity(encryptedPwd);
        return memberRepository.save(member);
    }

    /**
     * 로그인 아이디 중복 체크
     *
     * @param loginId 중복 체크할 로그인 아이디
     * @throws DuplicateException 이미 사용중인 로그인 아이디일 경우
     */
    private void duplicationCheckByLoginId(String loginId) {
        boolean checkLoginId = memberQueryRepository.existLoginId(loginId);
        if (checkLoginId) {
            throw new DuplicateException("이미 사용중인 아이디입니다.");
        }
    }

    /**
     * 전화번호 중복 체크
     *
     * @param phoneNumber 중복 체크 할 전화번호
     * @throws DuplicateException 이미 사용중인 전화번호일 경우
     */
    private void duplicationCheckByPhoneNumber(String phoneNumber) {
        boolean checkPhoneNumber = memberQueryRepository.existsPhoneNumber(phoneNumber);
        if (checkPhoneNumber) {
            throw new DuplicateException("이미 사용중인 전화번호입니다.");
        }
    }
}
