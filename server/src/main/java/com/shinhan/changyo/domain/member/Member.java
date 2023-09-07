package com.shinhan.changyo.domain.member;

import com.shinhan.changyo.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 회원 entity
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String encryptedPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(unique = true, nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean active;

    @Builder
    private Member(String loginId, String encryptedPwd, String name, String phoneNumber, Boolean active) {
        this.loginId = loginId;
        this.encryptedPwd = encryptedPwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }
}
